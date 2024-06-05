package com.user.UserMicroservice.service;

import com.user.UserMicroservice.dto.UserDTO;
import com.user.UserMicroservice.model.Role;
import com.user.UserMicroservice.model.User;
import com.user.UserMicroservice.repository.UserRepository;
import com.user.UserMicroservice.service.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<?> save(UserDTO user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User existentUser = optionalUser.get();
            if (!user.getPassword().equals("")) {
                existentUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            existentUser.setRole(user.getRole());
            existentUser.setUsername(user.getUsername());
            userRepository.save(existentUser);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJpYW4iLCJhdXRob3JpdGllcyI6WyJBRE1JTiJdfQ.m_8TcWhHFFUTgldQ7iewOYXYXNpUFYywzkEYVzPntsx7mLQOqV5Czw0yt7lES-rRg8mdOD_uV4VwPt7ibDl27w");
            ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://172.30.0.2:8081/devices/users/" + user.getId(), user.getId(), User.class);
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            User newUser = new User(user.getId(),user.getUsername(), passwordEncoder.encode(user.getPassword()), Role.USER);
            return ResponseEntity.ok(userRepository.save(newUser));
        }
    }

    public User getUser(String userId) {
        //get user from database with the help of user repository
        User user =  userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found" + userId));
        return user;
    }

    public ResponseEntity<?> getUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()) {
            User currentUser = user.get();
            return ResponseEntity.ok().body(currentUser);
        }
        return ResponseEntity.notFound().build();
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void updateUser(String userId, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + userId));

        // Update the existing user with data from updatedUserDTO
        existingUser.setPassword(updatedUserDTO.getPassword());
        existingUser.setRole(updatedUserDTO.getRole());
        existingUser.setUsername(updatedUserDTO.getUsername());

        userRepository.save(existingUser);
        LOGGER.debug("User with id {} was updated in db", userId);
    }

    public void deleteUser(String userId) {
        if (userRepository.existsById(Long.valueOf(userId))) {
            // Șterge id-ul utilizatorului din tabela "UserID" a microserviciului de Device
            restTemplate.exchange("http://172.30.0.2:8081/devices/users/delete/" + userId, HttpMethod.DELETE, null, String.class);
            userRepository.deleteById(Long.valueOf(userId));
            LOGGER.debug("User with id {} was deleted from db", userId);
        } else {
            LOGGER.error("User with id {} was not found in db", userId);
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + userId);
        }
    }

    public Long authenticateUser(String username, String password) throws AuthenticationException {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new AuthenticationException("Invalid username or password");
        }
        return user.getId();
    }
}
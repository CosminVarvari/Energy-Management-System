package com.user.service.UserService.services.impl;

import com.user.service.UserService.entities.User;
import com.user.service.UserService.exceptions.ResourceNotFoundException;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate = new RestTemplate();


    private static final Logger LOGGER =LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://172.30.0.2:8081/devices/users/" + user.getUserId(), user.getUserId(), User.class);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User getUser(String userId) {
        //get user from database with the help of user repoitory
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found" + userId));
        return user;
    }

    @Override
    public void deleteUser(String userId) throws IOException, URISyntaxException {

        if (userRepository.existsById(userId)) {
            User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found" + userId));
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://172.30.0.2:8081/devices/users/delete/" + user.getUserId(), HttpMethod.DELETE, null, String.class);
            userRepository.deleteById(userId);
        }else{
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + userId);
        }
    }

    @Override
    public User updateUser(String userId, User user) {
        // Check if the user exists
        if (userRepository.existsById(userId)) {
            // Get the existing user
            User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found: " + userId));

            // Update the user information with the provided data
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAbout(user.getAbout());
            existingUser.setUsername(user.getUsername());
            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + userId);
        }
    }

    @Override
    public User login(String username, String password) {
        // Find the user by username
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with the provided username");
        }

        // Verify the password (assuming plain text passwords for simplicity)
        if (!password.equals(user.getPassword())) {
            throw new ResourceNotFoundException("Invalid password");
        }

        return user;
    }


}

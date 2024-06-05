package com.user.UserMicroservice.loader;
import com.user.UserMicroservice.model.Role;
import com.user.UserMicroservice.model.User;
import com.user.UserMicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<User> users = Arrays.asList(
                new User(0L,"Marian","$2a$10$5CaHQOiSLXp20lYT3ugK5e0NVtXNDy0rJHMVS6Q4r7RyVZG9tIfWy", Role.ADMIN),
                new User(0L,"Andrei","$2a$10$gmtrs1G4TrYSz5N2OESlmuMcAukgAS1KIPMfX3/P49GE8l6jQsXOK",Role.ADMIN),
                new User(0L,"Cezar","$2a$10$gmtrs1G4TrYSz5N2OESlmuMcAukgAS1KIPMfX3/P49GE8l6jQsXOK",Role.USER),
                new User(0L,"Maia","$2a$10$gmtrs1G4TrYSz5N2OESlmuMcAukgAS1KIPMfX3/P49GE8l6jQsXOK",Role.USER)
        );

        userRepository.saveAll(users);

    }
}

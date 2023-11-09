package com.user.service.UserService.services;


import com.user.service.UserService.entities.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface UserService {

    //create

    User saveUser(User user);

    //get all users

    List<User> getAllUser();

    //get single user o given userId

    User getUser(String userId);

    //delete user

    void deleteUser(String userId) throws IOException, URISyntaxException;


    User updateUser(String userId, User user);

    User login(String username, String password);
}

package com.user.UserMicroservice.dto;

import com.user.UserMicroservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Role role;

}

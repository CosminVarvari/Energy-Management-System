package com.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name= "id")
    private String userId;
    @Column(name="NAME", length = 20)
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ABOUT")
    private String about;
    @Column(name="ROLE")
    private String role;
    @Column(name="USERNAME", unique = true)
    private String username;
    @Column(name="PASSWORD")
    private String password;

}

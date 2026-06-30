package com.makar.gamestoreapi.Controllers;


import com.makar.gamestoreapi.Models.User;
import com.makar.gamestoreapi.Services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> register(){
        return userService.getAllUsers();
    }


    @PostMapping("user/register")
    public User register(@RequestBody User user){
        return userService.addUser(user);
    }

}

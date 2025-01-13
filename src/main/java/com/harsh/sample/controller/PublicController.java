package com.harsh.sample.controller;


import com.harsh.sample.entity.User;
import com.harsh.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("/create-user")
    public void creteNewUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

}

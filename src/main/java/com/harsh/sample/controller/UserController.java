package com.harsh.sample.controller;

import com.harsh.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.harsh.sample.entity.User;
import com.harsh.sample.service.UserService;
// import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(user.getUserName());
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword()); 
            userService.saveUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepository.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
 
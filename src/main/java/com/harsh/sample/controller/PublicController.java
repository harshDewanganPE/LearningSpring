package com.harsh.sample.controller;


import com.harsh.sample.api.response.PincodeResponse;
import com.harsh.sample.entity.User;
import com.harsh.sample.service.PincodeService;
import com.harsh.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private PincodeService pincodeService;

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

    @GetMapping("postalinfo/{pin}")
    public ResponseEntity<?> postalInfo(@PathVariable String pin){

        List<PincodeResponse> res = pincodeService.getPostalInfo(pin);


        if(res == null || res.isEmpty() )
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);

    }

}

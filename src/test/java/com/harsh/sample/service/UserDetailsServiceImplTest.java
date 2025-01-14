package com.harsh.sample.service;

import com.harsh.sample.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {


    @InjectMocks
     private UserDetailsServiceImpl userDetailsService;

     @Mock
     private UserRepository userRepository;

     @BeforeEach
     void setUp(){
         MockitoAnnotations.initMocks(this);
     }

     @Test
     void loadUserByUsernameTest(){

         when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((com.harsh.sample.entity.User) User.builder().username("ram").password("inrinrick").build());
         UserDetails user = userDetailsService.loadUserByUsername("ram");
         Assertions.assertNotNull(user);

     }

}

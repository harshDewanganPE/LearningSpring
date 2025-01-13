package com.harsh.sample.config;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity


public class SpringSecurity {

    // @Autowired
    // private UserDetailsSe


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        
        
    }




}
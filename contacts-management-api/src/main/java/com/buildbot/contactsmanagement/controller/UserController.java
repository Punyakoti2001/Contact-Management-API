package com.buildbot.contactsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildbot.contactsmanagement.helper.ResponseConstants;
import com.buildbot.contactsmanagement.requestDto.LoginDto;
import com.buildbot.contactsmanagement.requestDto.UserDto;
import com.buildbot.contactsmanagement.responseDto.ResponseDto;
import com.buildbot.contactsmanagement.security.JwtService;
import com.buildbot.contactsmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/build-bot/v1/contact-user")
@Tag(name = "Contact User Management") 
@Slf4j
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/new-user")
    @Operation(summary = "Create a new user", description = "This endpoint allows creating a new user in the system.")
    public ResponseEntity<?> saveNewUser(@RequestBody UserDto userDto) {
        log.info("Request received to create a new user with username: {}", userDto.getUserName());

        userService.saveUser(userDto);
        log.info("User created successfully with username: {}", userDto.getUserName());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.CREATED, ResponseConstants.CREATED_SUCCESS));
    }

    @PostMapping("/get-token")
    @Operation(summary = "Authenticate and get token", description = "This endpoint allows users to authenticate with their username and password and receive a JWT token for accessing other secure endpoints.")
    public String authenticateAndGetToken(@RequestBody LoginDto login) {
        log.info("Authentication request received for username: {}", login.getUserName());

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(), login.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUserName());

        if (authenticate.isAuthenticated()) {
            log.info("Authentication successful for username: {}", login.getUserName());

            return jwtService.getToken(userDetails);
        }
        log.warn("Authentication failed for username: {}", login.getUserName());
        return "wrong userName and Password";
    }
}
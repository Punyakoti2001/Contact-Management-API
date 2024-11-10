package com.buildbot.contactsmanagement.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buildbot.contactsmanagement.entity.User;
import com.buildbot.contactsmanagement.exceptionhandling.ResourceAlreadyExistException;
import com.buildbot.contactsmanagement.mapper.UserMapper;
import com.buildbot.contactsmanagement.repository.UserRepository;
import com.buildbot.contactsmanagement.requestDto.UserDto;
import com.buildbot.contactsmanagement.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class implements the UserService interface and provides methods for user management.
 *
 * @Service indicates that this class is a Spring service bean.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Saves a new user to the database.
     *
     * @param userDto the user data to be saved
     * @throws ResourceAlreadyExistException if the username or email already exists in the database
     */
    @Override
    public void saveUser(UserDto userDto) {
        log.info("Attempting to save user with username: {} and email: {}", userDto.getUserName(), userDto.getEmail());

        Optional<User> byUserName = userRepository.findByUserName(userDto.getUserName());

        if (byUserName.isPresent()) {
            log.error("Username already taken: {}", userDto.getUserName());

            throw new ResourceAlreadyExistException("Username already taken: " + userDto.getUserName());
        }

        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if (byEmail.isPresent()) {
            log.error("Email already exists: {}", userDto.getEmail());

            throw new ResourceAlreadyExistException("Email already exists.");
        }

        User user = UserMapper.INSTANCE.mapToUser(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles().toUpperCase());
        log.info("User saved successfully with username: {}", userDto.getUserName());

        userRepository.save(user);
    }
}
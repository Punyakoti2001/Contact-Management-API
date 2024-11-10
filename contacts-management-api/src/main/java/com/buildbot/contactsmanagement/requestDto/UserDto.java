package com.buildbot.contactsmanagement.requestDto;

import com.buildbot.contactsmanagement.helper.UserConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class represents a Data Transfer Object (DTO) for user information.
 * It's used for transferring user data between the client and the server.
 *
 * @Data includes getters, setters, equals, hashCode, and toString methods from Lombok.
 * @AllArgsConstructor and @RequiredArgsConstructor are Lombok annotations for constructors.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

    /**
     * The username of the user.
     *
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     * @Pattern defines a regular expression for validation based on UserConstants.USERNAME_REGEX.
     * (Refer to UserConstants class for the actual regular expression and message.)
     */
    @NotBlank
    @Pattern(regexp = UserConstants.USERNAME_REGEX, message = UserConstants.USERNAME_MESSAGE)
    private String userName;

    /**
     * The password of the user.
     *
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     * @Pattern defines a regular expression for validation based on UserConstants.PASSWORD_REGEX.
     * (Refer to UserConstants class for the actual regular expression and message.)
     */
    @NotBlank
    @Pattern(regexp = UserConstants.PASSWORD_REGEX, message = UserConstants.PASSWORD_MESSAGE)
    private String password;

    /**
     * The email address of the user.
     *
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     * @Email validates the email address format.
     */
    @NotBlank
    @Email
    private String email;

    /**
     * The first name of the user.
     *
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     */
    @NotBlank
    private String firstName;

    /**
     * The last name of the user.
     *
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     */
    @NotBlank
    private String lastName;

    /**
     * The user's roles (comma-separated string).
     *
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     */
    @NotBlank
    private String roles;
}
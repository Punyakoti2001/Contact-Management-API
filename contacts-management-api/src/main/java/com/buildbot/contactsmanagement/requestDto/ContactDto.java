package com.buildbot.contactsmanagement.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class represents a Data Transfer Object (DTO) for contact information.
 * It's used for transferring contact data between the client and the server.
 *
 * @Data includes getters, setters, equals, hashCode, and toString methods from Lombok.
 * @AllArgsConstructor and @RequiredArgsConstructor are Lombok annotations for constructors.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ContactDto {

    /**
     * The first name of the contact.
     *
     * @NotNull ensures the field cannot be null.
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     * @NotEmpty ensures the field cannot be null or an empty collection.
     * @Pattern defines a regular expression to validate the format (letters and spaces only).
     * @Size specifies the maximum length of the field (50 characters).
     */
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z ]+")
    @Size(max = 50)
    private String firstName;

    /**
     * The last name of the contact.
     *
     * Same validation constraints as firstName.
     */

    @Pattern(regexp = "[a-zA-Z ]+")
    @Size(max = 50)
    private String lastName;

    /**
     * The email address of the contact.
     *
     * @NotNull, @NotBlank, and @NotEmpty for null/empty value checks.
     * @Email validates the email address format.
     * @Pattern provides a custom regular expression for email format validation.
     * (Consider using a dedicated email validation library for more robust validation).
     */
    @NotNull
    @NotBlank
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Invalid email format.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email should be in the format: ^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    /**
     * The phone number of the contact.
     *
     * Same validation constraints as email with a custom regular expression for phone number format.
     * (Consider using a dedicated phone number validation library for more robust validation).
     */
    @NotNull
    @NotBlank
    @NotEmpty(message = "Phone number cannot be empty.")
    @Pattern(regexp = "(^\\+91[6-9]\\d{9}$)|(^[6-9]\\d{9}$)|(^\\+[0-9]{1,2}[0-9]{10,}$)", message = "Phone number must match the format: (^\\+91[6-9]\\d{9}$)|(^[6-9]\\d{9}$)|(^\\+[0-9]{1,2}[0-9]{10,}$)")
    private String phoneNo;
}
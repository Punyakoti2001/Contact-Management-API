package com.buildbot.contactsmanagement.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * This class represents a Data Transfer Object (DTO) for address information.
 * It's used for transferring address data between the client and the server.
 */
public class AddressDto {

    /**
     * The street name of the address.
     *
     * @NotNull, @NotBlank, and @NotEmpty ensure the field is not null or empty.
     * @Size limits the maximum length to 100 characters.
     */
    @NotNull(message = "Street name is required")
    @NotBlank(message = "Street name cannot be blank")
    @NotEmpty(message = "Street name cannot be empty")
    @Size(max = 100, message = "Street name cannot exceed 100 characters")
    private String streetName;

    /**
     * The city of the address.
     *
     * Same validation constraints as street name.
     * @Pattern ensures the city name only contains letters.
     */
    @NotNull(message = "City is required")
    @NotBlank(message = "City cannot be blank")
    @NotEmpty(message = "City cannot be empty")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "City can only contain letters")
    private String city;

    /**
     * The state of the address.
     *
     * Same validation constraints as city.
     */
    @NotNull(message = "State is required")
    @NotBlank(message = "State cannot be blank")
    @NotEmpty(message = "State cannot be empty")
    @Size(max = 100, message = "State cannot exceed 50 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "State can only contain letters")
    private String state;

    /**
     * The PIN code of the address.
     *
     * Same validation constraints as other fields.
     * @Pattern ensures the PIN code matches the specified format (6 digits or 6 digits with a 4-digit hyphenated extension).
     */
    @NotNull(message = "PIN code is required")
    @NotBlank(message = "PIN code cannot be blank")
    @NotEmpty(message = "PIN code cannot be empty")
    @Pattern(regexp = "(\\d{6})|(\\d{6}(-\\d{4})?)", message = "PIN code must be match with given formate.(\\d{6})|(\\d{6}(-\\d{4})?) ")
    private String pinCode;

    /**
     * The country of the address.
     *
     * Same validation constraints as city and state.
     */
    @NotNull(message = "Country is required")
    @NotBlank(message = "Country cannot be blank")
    @NotEmpty(message = "Country cannot be empty")
    @Size(max = 100, message = "Country cannot exceed 50 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "Country can only contain letters")
    private String country;

    /**
     * The ID of the contact associated with this address.
     *
     * @NotNull ensures the field cannot be null.
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     */
    @NotNull
    @NotBlank
    private Integer contactId;
}
package com.buildbot.contactsmanagement.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginDto {

    /**
     * The username of the user attempting to login.
     *
     * @NotNull ensures the field cannot be null.
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     */
    @NotNull
    @NotBlank
    private String userName;

    /**
     * The password of the user attempting to login.
     *
     * @NotNull ensures the field cannot be null.
     * @NotBlank ensures the field cannot be null or contain only whitespace characters.
     * @NotEmpty ensures the field cannot be null or an empty collection (redundant with @NotBlank).
     */
    @NotNull
    @NotBlank
    @NotEmpty // (Redundant with @NotBlank)
    private String password;
}

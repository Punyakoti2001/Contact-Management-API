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

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ContactDto {

	@NotNull
	@NotBlank
	@NotEmpty
	@Pattern(regexp = "[a-zA-Z ]+")
	@Size(max = 50)
	private String firstName;

	

	@NotNull
	@NotBlank
	@NotEmpty
	@Pattern(regexp = "[a-zA-Z ]+")
	@Size(max = 50)
	private String lastName;

	@NotNull
	@NotBlank
	@NotEmpty(message = "Email cannot be Empty")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email should me in the given format ^[A-Za-z0-9+_.-]+@(.+)$")
	private String email;

	@NotNull
	@NotBlank
	@NotEmpty(message = "phoneNo cannot be Empty")
	@Pattern(regexp = "(^\\+91[6-9]\\d{9}$)|(^[6-9]\\d{9}$)|(^\\+[0-9]{1,2}[0-9]{10,}$)", message = "phoneNo must be match to the given format (^\\\\+91[6-9]\\\\d{9}$)|(^[6-9]\\\\d{9}$)|(^\\\\+[0-9]{1,2}[0-9]{10,}$)")
	private String phoneNo;

}
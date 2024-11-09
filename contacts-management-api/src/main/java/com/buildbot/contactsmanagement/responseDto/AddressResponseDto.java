package com.buildbot.contactsmanagement.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AddressResponseDto {

	private Long addressId;
	
	private String streetName;
	
	private String city;

	private String state;

	private String pinCode;

	private String country;

	private Integer contactId;

}

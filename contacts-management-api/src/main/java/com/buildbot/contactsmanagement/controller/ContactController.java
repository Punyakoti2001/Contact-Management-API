package com.buildbot.contactsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buildbot.contactsmanagement.ResponseConstants;
import com.buildbot.contactsmanagement.requestDto.ContactDto;
import com.buildbot.contactsmanagement.responseDto.ContactResponseDto;
import com.buildbot.contactsmanagement.responseDto.ResponseDto;
import com.buildbot.contactsmanagement.service.ContactService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/build-bot/v1/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;

	@PostMapping("/create-contact")
	public ResponseEntity<?> createNewContact(@RequestBody @Validated ContactDto contactDto) {

		ContactResponseDto newResource = contactService.createNewResource(contactDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED, ResponseConstants.CREATED_SUCCESS));

	}

	@PutMapping("/update-contact/{contactId}")
	public ResponseEntity<?> updateContact(
			@Validated @Pattern(regexp = "\\d+") @PathVariable(required = true) String contactId,
			@RequestBody @Validated ContactDto contactDto) {
		Long id = Long.parseLong(contactId);
		ContactResponseDto updateExistingResource = contactService.updateExistingResource(id, contactDto);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK, ResponseConstants.UPDATED_SUCCESS));

	}

	@GetMapping(path = {"/fetchByField","/fetch-all-contacts"})
	public ResponseEntity<?> fetchByRequiredField(@RequestParam(required = false) String phoneNo,
			@RequestParam(required = false) String email) {
		
		
		
		if(phoneNo == null && email == null)
		{
		List<ContactResponseDto> result = contactService.fetchAllContacts();
		return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		else if(phoneNo != null && email == null)
		{
			ContactResponseDto result = contactService.fetchContactByPhoneNo(phoneNo);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		else if(phoneNo == null && email != null){
			ContactResponseDto result = contactService.fetchContactByEmail(email);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		else {
	        return ResponseEntity.badRequest()
	                .body("Please provide only one of either phoneNo or email, not both.");
	    }
		
		

	}

}

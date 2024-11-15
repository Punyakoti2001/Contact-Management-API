package com.buildbot.contactsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.buildbot.contactsmanagement.helper.ResponseConstants;
import com.buildbot.contactsmanagement.requestDto.ContactDto;
import com.buildbot.contactsmanagement.responseDto.ContactResponseDto;
import com.buildbot.contactsmanagement.responseDto.ResponseDto;
import com.buildbot.contactsmanagement.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/build-bot/v1/contact")
@Tag(name = "Contact Management", description = "API for managing contact information")
@Slf4j
@CrossOrigin("http://localhost:3000")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@Operation(summary = "Create a new contact", description = "Creates a new contact with the provided details")
	@PostMapping("/create-contact")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> createNewContact(@RequestBody @Validated ContactDto contactDto) {
		log.info("Request to create a new contact received with data: {}", contactDto);

		ContactResponseDto newResource = contactService.createNewResource(contactDto);
		log.info("Contact created successfully with ID: {}", newResource.getContactId());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED, ResponseConstants.CREATED_SUCCESS));
	}

	@Operation(summary = "Update an existing contact", description = "Updates the details of an existing contact")
	@PutMapping("/update-contact/{contactId}")
	public ResponseEntity<?> updateContact(
			@Parameter(description = "ID of the contact to update", required = true) @Validated @Pattern(regexp = "\\d+") @PathVariable String contactId,
			@RequestBody @Validated ContactDto contactDto) {
        log.info("Request to update contact received with ID: {} and data: {}", contactId, contactDto);

		Long id = Long.parseLong(contactId);
		ContactResponseDto updateExistingResource = contactService.updateExistingResource(id, contactDto);
        log.info("Contact updated successfully with ID: {}", id);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK, ResponseConstants.UPDATED_SUCCESS));
	}

	@Operation(summary = "Fetch contacts by field", description = "Fetches contacts by phone number or email, or retrieves all contacts if no parameters are provided")
	@GetMapping(path = { "/fetchByField", "/fetch-all-contacts" })
	public ResponseEntity<?> fetchByRequiredField(
			@Parameter(description = "Phone number to filter by") @RequestParam(required = false) String phoneNo,
			@Parameter(description = "Email to filter by") @RequestParam(required = false) String email) {
        log.info("Request to fetch contacts with phoneNo: {} or email: {}", phoneNo, email);

		if (phoneNo == null && email == null) {
			List<ContactResponseDto> result = contactService.fetchAllContacts();
			
            log.info("Fetched all contacts. Total count: {}", result.size());

			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else if (phoneNo != null && email == null) {
			ContactResponseDto result = contactService.fetchContactByPhoneNo(phoneNo);
            log.info("Fetched contact by phoneNo: {}", phoneNo);

			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else if (phoneNo == null && email != null) {
			ContactResponseDto result = contactService.fetchContactByEmail(email);
            log.info("Fetched contact by email: {}", email);

			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
            log.error("Invalid request: both phoneNo and email provided. Only one is allowed.");

			return ResponseEntity.badRequest().body("Please provide only one of either phoneNo or email, not both.");
		}
	}

	@Operation(summary = "Delete a contact", description = "Deletes a contact by its ID")
	@DeleteMapping("/delete-contact")
	public ResponseEntity<?> deleteContact(
			@Parameter(description = "ID of the contact to delete", required = true) @RequestParam Long contactId) {
        log.info("Request to delete contact with ID: {}", contactId);

		contactService.deleteContact(contactId);
        log.info("Contact deleted successfully with ID: {}", contactId);

		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(HttpStatus.OK, ResponseConstants.DELETE_SUCCESS + "with ContactId : " + contactId));
	}

	@Operation(summary = "merge contact", description = "Merge the duplicate contact")
	@PostMapping("/merge-contacts")
	public ResponseEntity<ContactResponseDto> mergeContacts(@RequestParam Long primaryContactId,
			@RequestParam List<Long> duplicateContactIds) {
        log.info("Request to merge contacts received with primaryContactId: {} and duplicateContactIds: {}", primaryContactId, duplicateContactIds);

		ContactResponseDto mergedContact = contactService.mergeContacts(primaryContactId, duplicateContactIds);
        log.info("Contacts merged successfully into primaryContactId: {}", primaryContactId);

		return ResponseEntity.ok(mergedContact);
	}

}

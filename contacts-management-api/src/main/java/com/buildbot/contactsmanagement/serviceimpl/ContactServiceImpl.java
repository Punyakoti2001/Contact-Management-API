package com.buildbot.contactsmanagement.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.buildbot.contactsmanagement.entity.Contact;
import com.buildbot.contactsmanagement.exceptionhandling.ResourceAlreadyExistException;
import com.buildbot.contactsmanagement.exceptionhandling.ResourceNotFoundException;
import com.buildbot.contactsmanagement.mapper.ContactMapper;
import com.buildbot.contactsmanagement.repository.ContactRepository;
import com.buildbot.contactsmanagement.requestDto.ContactDto;
import com.buildbot.contactsmanagement.responseDto.ContactResponseDto;
import com.buildbot.contactsmanagement.service.ContactService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the ContactService interface for managing contacts. Handles
 * CRUD operations, caching, and asynchronous processing.
 */
@Service
@Slf4j
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	/**
	 * Creates a new contact if the email and phone number do not already exist.
	 *
	 * @param contactDto Data transfer object containing contact details
	 * @return ContactResponseDto containing details of the created contact
	 * @throws ResourceAlreadyExistException if the email or phone number already
	 *                                       exists
	 */
	@Override
	@Transactional
	public ContactResponseDto createNewResource(ContactDto contactDto) {
		log.info("Creating new contact with email: {}", contactDto.getEmail());

		Optional<Contact> existEmail = contactRepository.findByEmail(contactDto.getEmail());
		Optional<Contact> existPhoneNo = contactRepository.findByPhoneNo(contactDto.getPhoneNo());

		if (existEmail.isPresent()) {
			log.error("Email already exists: {}", contactDto.getEmail());

			throw new ResourceAlreadyExistException("Email Already Exist");
		}

		if (existPhoneNo.isPresent()) {
			log.error("Phone number already exists: {}", contactDto.getPhoneNo());

			throw new ResourceAlreadyExistException("PhoneNo Already Exist");
		}

		Contact contact = ContactMapper.INSTANCE.mapToContact(contactDto);
		Contact save = contactRepository.save(contact);
		log.info("Contact created successfully with ID: {}", save.getContactId());

		return ContactMapper.INSTANCE.mapToContactResponseDto(save);
	}

	/**
	 * Updates an existing contact by its ID if the email and phone number are
	 * unique to this contact.
	 *
	 * @param contactId  ID of the contact to be updated
	 * @param contactDto Data transfer object containing updated contact details
	 * @return ContactResponseDto containing details of the updated contact
	 * @throws ResourceNotFoundException     if the contact with specified ID is not
	 *                                       found
	 * @throws ResourceAlreadyExistException if the email or phone number exists for
	 *                                       another contact
	 */
	@Override
	@Transactional
	@Async
	@CacheEvict(value = "contactsCache", allEntries = true)
	public ContactResponseDto updateExistingResource(Long contactId, ContactDto contactDto) {
		log.info("Updating contact with ID: {}", contactId);

		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", "ContactId", contactId));

		Optional<Contact> existEmail = contactRepository.findByEmail(contactDto.getEmail());
		if (existEmail.isPresent() && !existEmail.get().getContactId().equals(contactId)) {
			log.error("Email already exists for another contact: {}", contactDto.getEmail());

			throw new ResourceAlreadyExistException("Email already exists for another contact");
		}

		Optional<Contact> existPhoneNo = contactRepository.findByPhoneNo(contactDto.getPhoneNo());
		if (existPhoneNo.isPresent() && !existPhoneNo.get().getContactId().equals(contactId)) {
			log.error("Phone number already exists for another contact: {}", contactDto.getPhoneNo());

			throw new ResourceAlreadyExistException("Phone number already exists for another contact");
		}

		Contact updatedContact = ContactMapper.INSTANCE.updateMapToContact(contactDto, contact);
		Contact update = contactRepository.save(updatedContact);
		log.info("Contact updated successfully with ID: {}", update.getContactId());

		return ContactMapper.INSTANCE.mapToContactResponseDto(update);
	}

	/**
	 * Retrieves all contacts in the system.
	 *
	 * @return List of ContactResponseDto containing details of all contacts
	 */
	@Override
	@Async
	@Cacheable(value = "contactCache", key = "'allContacts'")
	public List<ContactResponseDto> fetchAllContacts() {
		log.info("Fetching all contacts.");

		List<Contact> all = contactRepository.findAll();
		log.info("Fetched {} contacts from the database.", all.size());

		return ContactMapper.INSTANCE.listMapToContactResponseDto(all);
	}

	/**
	 * Retrieves a contact by its phone number.
	 *
	 * @param phoneNo Phone number to filter by
	 * @return ContactResponseDto containing details of the contact with the
	 *         specified phone number
	 * @throws ResourceNotFoundException if no contact with the specified phone
	 *                                   number is found
	 */
	@Override
	@Async
	@Cacheable(value = "contactCache", key = "#phoneNo")
	public ContactResponseDto fetchContactByPhoneNo(String phoneNo) {
		log.info("Fetching contact by phone number: {}", phoneNo);

		Contact contact = contactRepository.findByPhoneNo(phoneNo).orElseThrow(() -> {
			log.error("Contact not found for phone number: {}", phoneNo);

			return new ResourceNotFoundException("Contact", "PhoneNo", phoneNo);

		});
		log.info("Fetched contact with phone number: {}", phoneNo);

		return ContactMapper.INSTANCE.mapToContactResponseDto(contact);
	}

	/**
	 * Retrieves a contact by its email.
	 *
	 * @param email Email to filter by
	 * @return ContactResponseDto containing details of the contact with the
	 *         specified email
	 * @throws ResourceNotFoundException if no contact with the specified email is
	 *                                   found
	 */
	@Override
	@Async
	@Cacheable(value = "contactCache", key = "#email")
	public ContactResponseDto fetchContactByEmail(String email) {
		log.info("Fetching contact by email: {}", email);

		Contact contact = contactRepository.findByEmail(email).orElseThrow(() -> {
			log.error("Contact not found for email: {}", email);

			return new ResourceNotFoundException("Contact", "Email", email);
		});
		log.info("Fetched contact with email: {}", email);

		return ContactMapper.INSTANCE.mapToContactResponseDto(contact);
	}

	/**
	 * Deletes a contact by its ID.
	 *
	 * @param contactId ID of the contact to delete
	 * @throws ResourceNotFoundException if no contact with the specified ID is
	 *                                   found
	 */
	@Override
	@Async
	@Transactional
	@CacheEvict(value = "contactCache",allEntries = true)
	public void deleteContact(Long contactId) {
		log.info("Deleting contact with ID: {}", contactId);

		Contact contact = contactRepository.findById(contactId).orElseThrow(() -> {
			log.error("Contact not found for ID: {}", contactId);

			return new ResourceNotFoundException("Contact", "ContactId", contactId);
		});
		contactRepository.deleteById(contactId);
		log.info("Contact deleted successfully with ID: {}", contactId);

	}

	/**
	 * Merges duplicate contacts into a primary contact by filling in missing
	 * fields.
	 *
	 * @param primaryContactId    ID of the primary contact to retain.
	 * @param duplicateContactIds List of IDs of duplicate contacts to merge.
	 * @return The updated primary contact with merged data.
	 */
	@Override
	@Transactional
	public ContactResponseDto mergeContacts(Long primaryContactId, List<Long> duplicateContactIds) {
		log.info("Merging contacts with primaryContactId: {} and duplicateContactIds: {}", primaryContactId,
				duplicateContactIds);

		Contact primaryContact = contactRepository.findById(primaryContactId).orElseThrow(() -> {
			log.error("Primary contact not found for ID: {}", primaryContactId);
			return new ResourceNotFoundException("Contact", "ContactId", primaryContactId);
		});

		List<Contact> duplicates = contactRepository.findAllById(duplicateContactIds);

		for (Contact duplicate : duplicates) {
			if (primaryContact.getFirstName() == null && duplicate.getFirstName() != null) {
				primaryContact.setFirstName(duplicate.getFirstName());
			}
			if (primaryContact.getLastName() == null && duplicate.getLastName() != null) {
				primaryContact.setLastName(duplicate.getLastName());
			}
			if (primaryContact.getEmail() == null && duplicate.getEmail() != null) {
				primaryContact.setEmail(duplicate.getEmail());
			}
			if (primaryContact.getPhoneNo() == null && duplicate.getPhoneNo() != null) {
				primaryContact.setPhoneNo(duplicate.getPhoneNo());
			}
			if (primaryContact.getAddress() == null && duplicate.getAddress() != null) {
				primaryContact.setAddress(duplicate.getAddress());
			}
		}

		Contact save = contactRepository.save(primaryContact);

		contactRepository.deleteAll(duplicates);

		ContactResponseDto responseDto = ContactMapper.INSTANCE.mapToContactResponseDto(save);
        log.info("Merged contacts successfully. Primary contact ID: {}", primaryContactId);

		return responseDto;
	}
}

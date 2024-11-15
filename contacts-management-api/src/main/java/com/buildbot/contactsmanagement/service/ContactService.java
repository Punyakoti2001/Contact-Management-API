package com.buildbot.contactsmanagement.service;

import com.buildbot.contactsmanagement.requestDto.ContactDto;
import com.buildbot.contactsmanagement.responseDto.ContactResponseDto;

import java.util.List;

/**
 * This interface defines the service layer for contact management.
 * It provides methods for creating, updating, fetching, and deleting contacts.
 */
public interface ContactService {

    /**
     * Creates a new contact.
     *
     * @param contactDto the contact data to be created
     * @return the created contact as a ContactResponseDto
     */
    ContactResponseDto createNewResource(ContactDto contactDto);

    /**
     * Updates an existing contact.
     *
     * @param contactId the ID of the contact to be updated
     * @param contactDto the updated contact data
     * @return the updated contact as a ContactResponseDto
     */
    ContactResponseDto updateExistingResource(Long contactId, ContactDto contactDto);

    /**
     * Fetches a list of all contacts.
     *
     * @return a list of ContactResponseDto objects representing all contacts
     */
    List<ContactResponseDto> fetchAllContacts();

    /**
     * Fetches a contact by their phone number.
     *
     * @param phoneNo the phone number of the contact
     * @return the contact as a ContactResponseDto, or null if not found
     */
    ContactResponseDto fetchContactByPhoneNo(String phoneNo);

    /**
     * Fetches a contact by their email address.
     *
     * @param email the email address of the contact
     * @return the contact as a ContactResponseDto, or null if not found
     */
    ContactResponseDto fetchContactByEmail(String email);

    /**
     * Deletes a contact by their ID.
     *
     * @param contactId the ID of the contact to be deleted
     */
    void deleteContact(Long contactId);
    
    ContactResponseDto mergeContacts(Long primaryContactId, List<Long> duplicateContactIds);
}
package com.buildbot.contactsmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.buildbot.contactsmanagement.entity.Contact;
import com.buildbot.contactsmanagement.requestDto.ContactDto;
import com.buildbot.contactsmanagement.responseDto.ContactResponseDto;

@Mapper
public interface ContactMapper 
{
	ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
	
	
    @Mapping(target = "firstName", source = "firstName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "lastName", source = "lastName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNo", source = "phoneNo")
	Contact mapToContact(ContactDto contactDto);
	
	@Named("replaceAndTrim")
    default String replaceAndTrim(String input)
	{
    	if(input!=null) {
	        String doubleSpaces = input.replaceAll("\\s+", " ");
	      
	        String trimmedString = doubleSpaces.trim();
	        
			return trimmedString.toUpperCase();
    	}
    	return null;
	}

    @Mapping(target = "contactId",source="contactId")
    @Mapping(target = "firstName", source = "firstName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "lastName", source = "lastName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNo", source = "phoneNo")
	ContactResponseDto mapToContactResponseDto(Contact save);

    
    @Mapping(target = "contactId",ignore = true)
    @Mapping(target = "firstName", source = "firstName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "lastName", source = "lastName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNo", source = "phoneNo")
	Contact updateMapToContact(ContactDto contactDto,@MappingTarget Contact contact);

    @Mapping(target = "contactId",source = "contactId")
    @Mapping(target = "firstName", source = "firstName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "lastName", source = "lastName",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "email", source = "email",qualifiedByName = "replaceAndTrim")
    @Mapping(target = "phoneNo", source = "phoneNo",qualifiedByName = "replaceAndTrim")
	List<ContactResponseDto> listMapToContactResponseDto(List<Contact> all);
}

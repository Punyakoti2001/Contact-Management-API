package com.buildbot.contactsmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.buildbot.contactsmanagement.entity.User;
import com.buildbot.contactsmanagement.requestDto.UserDto;

import jakarta.persistence.Column;

@Mapper
public interface UserMapper 
{
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "firstName",source = "firstName",qualifiedByName = "replaceAndTrim")
	@Mapping(target = "lastName",source = "lastName",qualifiedByName = "replaceAndTrim")
	@Mapping(target = "email",source = "email",qualifiedByName = "replaceAndTrim")
	User mapToUser(UserDto userDto);
	
	
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


}

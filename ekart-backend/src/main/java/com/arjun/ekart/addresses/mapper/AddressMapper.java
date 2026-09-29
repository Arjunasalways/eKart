package com.arjun.ekart.addresses.mapper;

import org.mapstruct.BeanMapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.arjun.ekart.addresses.dto.AddressCreationRequest;
import com.arjun.ekart.addresses.dto.AddressResponse;
import com.arjun.ekart.addresses.dto.AddressUpdateRequest;
import com.arjun.ekart.addresses.entity.Address;

@Mapper(componentModel="spring")
public interface AddressMapper {
	
	AddressResponse toResponse(Address address);
	
	@Mapping(target="user" , ignore = true)
	Address toEntity(AddressCreationRequest request);
	
	 @Mapping(target="user" , ignore=true)
	 @BeanMapping(
				nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
				)
	 void updateEntity(@MappingTarget Address address,AddressUpdateRequest request);
	
}

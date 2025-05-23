package br.com.administrator.mappers.person;

import org.modelmapper.ModelMapper;

import br.com.administrator.mappers.person.converters.EnumUserTypeConverter;
import br.com.administrator.mappers.person.converters.LabeledUserTypeEnumToEnumConverter;
import br.com.administrator.to.TOPerson;
import br.com.administrator.to.TOUser;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.UserDTO;

public final class PersonMapper {

	private final ModelMapper modelMapper;

	public PersonMapper(EnumUserTypeConverter userTypeConverter, LabeledUserTypeEnumToEnumConverter labeledEnumConverter) {
		this.modelMapper = new ModelMapper();

		modelMapper.addConverter(labeledEnumConverter);
		modelMapper.addConverter(userTypeConverter);
	}
	
	public PersonDTO getPersonDTOFrom(TOPerson toPerson) {
		return modelMapper.map(toPerson, PersonDTO.class);
	}
	
	public TOPerson getTOPersonFrom(PersonDTO personDTO) {
		return modelMapper.map(personDTO, TOPerson.class);
	}
	
	public UserDTO getUserDTOFrom(TOUser toUser) {
		return modelMapper.map(toUser, UserDTO.class);
	}
	
	public TOUser getTOUserFrom(UserDTO userDTO) {
		return modelMapper.map(userDTO, TOUser.class);
	}
}

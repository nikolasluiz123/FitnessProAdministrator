package br.com.administrator.mappers;

import org.modelmapper.ModelMapper;

import br.com.administrator.to.TOApplication;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;

public final class ApplicationMapper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public ApplicationDTO getApplicationDTOFrom(TOApplication toApplication) {
		return modelMapper.map(toApplication, ApplicationDTO.class);
	}
	
	public TOApplication getTOApplicationFrom(ApplicationDTO ApplicationDTO) {
		return modelMapper.map(ApplicationDTO, TOApplication.class);
	}
}

package br.com.administrator.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

import br.com.administrator.to.TOApplication;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;

@ApplicationScoped
public class ApplicationMapper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public ApplicationDTO getApplicationDTOFrom(TOApplication toApplication) {
		return modelMapper.map(toApplication, ApplicationDTO.class);
	}
	
	public TOApplication getTOApplicationFrom(ApplicationDTO applicationDTO) {
		return modelMapper.map(applicationDTO, TOApplication.class);
	}
}

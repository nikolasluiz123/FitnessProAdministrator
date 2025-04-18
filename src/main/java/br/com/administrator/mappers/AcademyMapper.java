package br.com.administrator.mappers;

import org.modelmapper.ModelMapper;

import br.com.administrator.to.TOAcademy;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;

public final class AcademyMapper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public AcademyDTO getAcademyDTOFrom(TOAcademy toAcademy) {
		return modelMapper.map(toAcademy, AcademyDTO.class);
	}
	
	public TOAcademy getTOAcademyFrom(AcademyDTO academyDTO) {
		return modelMapper.map(academyDTO, TOAcademy.class);
	}
}

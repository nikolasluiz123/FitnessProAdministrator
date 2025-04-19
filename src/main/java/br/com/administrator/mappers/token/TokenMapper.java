package br.com.administrator.mappers.token;

import org.modelmapper.ModelMapper;

import br.com.administrator.mappers.token.converters.EnumTokenTypeConverter;
import br.com.administrator.mappers.token.converters.LabeledTokenTypeEnumToEnumConverter;
import br.com.administrator.to.TOServiceToken;
import br.com.administrator.to.TOServiceTokenGeneration;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenGenerationDTO;

public final class TokenMapper {

	private final ModelMapper modelMapper;

	public TokenMapper(EnumTokenTypeConverter tokenTypeConverter, LabeledTokenTypeEnumToEnumConverter labeledEnumConverter) {
		this.modelMapper = new ModelMapper();

		modelMapper.addConverter(tokenTypeConverter);
		modelMapper.addConverter(labeledEnumConverter);
	}

	public ServiceTokenGenerationDTO getServiceTokenGenerationDTOFrom(TOServiceTokenGeneration toServiceTokenGeneration) {
		return modelMapper.map(toServiceTokenGeneration, ServiceTokenGenerationDTO.class);
	}

	public TOServiceTokenGeneration getTOServiceTokenGenerationFrom(ServiceTokenGenerationDTO serviceTokenGenerationDTO) {
		return modelMapper.map(serviceTokenGenerationDTO, TOServiceTokenGeneration.class);
	}

	public ServiceTokenDTO getServiceTokenDTOFrom(TOServiceToken toServiceToken) {
		return modelMapper.map(toServiceToken, ServiceTokenDTO.class);
	}

	public TOServiceToken getTOServiceTokenFrom(ServiceTokenDTO serviceTokenDTO) {
		return modelMapper.map(serviceTokenDTO, TOServiceToken.class);
	}

}

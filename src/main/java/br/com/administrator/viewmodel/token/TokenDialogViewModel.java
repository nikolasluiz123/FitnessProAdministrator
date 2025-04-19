package br.com.administrator.viewmodel.token;

import java.io.Serializable;

import br.com.administrator.mappers.labeledenum.execution.TokenLabeledEnumMapper;
import br.com.administrator.mappers.token.TokenMapper;
import br.com.administrator.service.webclient.TokenWebClient;
import br.com.administrator.to.TOServiceTokenGeneration;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenGenerationDTO;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class TokenDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final TokenWebClient webClient;
	private final TokenLabeledEnumMapper labeledEnumMapper;
	private final TokenMapper mapper;

	@Inject
	public TokenDialogViewModel(TokenWebClient webClient, TokenLabeledEnumMapper labeledEnumMapper, TokenMapper mapper) {
		this.webClient = webClient;
		this.labeledEnumMapper = labeledEnumMapper;
		this.mapper = mapper;
	}
	
	public void generateToken(TOServiceTokenGeneration to) throws Exception {
		ServiceTokenGenerationDTO dto = mapper.getServiceTokenGenerationDTOFrom(to);
		
		SingleValueServiceResponse<ServiceTokenDTO> response = webClient.generateToken(dto);
		
		if (response.getSuccess()) {
			to.setId(response.getValue().getId());
		}
	}
	
	public String getLabelTokenType(EnumTokenType type) {
		return this.labeledEnumMapper.getLabelTokenType(type);
	}
	
}

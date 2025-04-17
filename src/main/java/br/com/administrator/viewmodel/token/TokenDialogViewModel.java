package br.com.administrator.viewmodel.token;

import java.io.Serializable;
import java.util.ResourceBundle;

import br.com.administrator.service.webclient.TokenWebClient;
import br.com.administrator.to.TOServiceTokenGeneration;
import br.com.administrator.utils.FacesUtils;
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

	@Inject
	public TokenDialogViewModel(TokenWebClient webClient) {
		this.webClient = webClient;
	}
	
	public void generateToken(TOServiceTokenGeneration to) throws Exception {
		ServiceTokenGenerationDTO dto = getServiceTokenGenerationDTO(to);
		
		SingleValueServiceResponse<ServiceTokenDTO> response = webClient.generateToken(dto);
		
		if (response.getSuccess()) {
			to.setId(response.getValue().getId());
		}
	}

	private ServiceTokenGenerationDTO getServiceTokenGenerationDTO(TOServiceTokenGeneration to) {
		ServiceTokenGenerationDTO dto = new ServiceTokenGenerationDTO();
		
		if (to.getSelectedApplication() != null) {
			dto.setApplicationId(to.getSelectedApplication().getId());
		}
		
		if (to.getSelectedDevice() != null) {
			dto.setDeviceId(to.getSelectedDevice().getId());
		}
		
		if (to.getSelectedUser() != null) {
			dto.setUserId(to.getSelectedUser().getId());
		}
		
		dto.setType(to.getType());
		
		return dto;
	}
	
	public String getLabelTokenType(EnumTokenType type) {
		ResourceBundle bundle = FacesUtils.getResourceBundle("token_search");
		String label = null;
		
		switch (type) {
		case APPLICATION_TOKEN:
			label = bundle.getString("label_type_token_application");
			break;
		case DEVICE_TOKEN:
			label = bundle.getString("label_type_token_device");
			break;
		case USER_AUTHENTICATION_TOKEN:
			label = bundle.getString("label_type_token_user");
			break;
		default:
			break;
		}
		
		return label;
	}
}

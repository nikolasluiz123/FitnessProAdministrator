package br.com.administrator.viewmodel.token;

import java.io.Serializable;
import java.util.ResourceBundle;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.service.webclient.TokenWebClient;
import br.com.administrator.to.TOApplication;
import br.com.administrator.to.TODevice;
import br.com.administrator.to.TOServiceToken;
import br.com.administrator.to.TOUser;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.dtos.general.UserDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class TokenDialogReadViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final TokenWebClient webClient;

	@Inject
	public TokenDialogReadViewModel(TokenWebClient webClient) {
		this.webClient = webClient;
	}
	
	public void invalidateToken(String tokenId) throws Exception {
		webClient.invalidateToken(tokenId);
	}
	
	public TOServiceToken getTokenService(String tokenId) throws Exception {
		return getServiceTokenTO(webClient.getTokenService(tokenId));
	}
	
	private TOServiceToken getServiceTokenTO(ServiceTokenDTO dto) {
		TOServiceToken to = new TOServiceToken();
		to.setId(dto.getId());
		to.setCreationDate(dto.getCreationDate());
		to.setExpirationDate(dto.getExpirationDate());
		to.setJwtToken(dto.getJwtToken());
		to.setType(new LabeledEnum<EnumTokenType>(dto.getType(), getLabelTokenType(dto.getType())));
		
		ApplicationDTO applicationDTO = dto.getApplicationDTO();
		
		if (applicationDTO != null) {
			TOApplication toApplication = new TOApplication();
			toApplication.setActive(applicationDTO.getActive());
			toApplication.setId(applicationDTO.getId());
			toApplication.setName(applicationDTO.getName());
			
			to.setToApplication(toApplication);
		}
		
		DeviceDTO deviceDTO = dto.getDeviceDTO();
		
		if (deviceDTO != null) {
			TODevice toDevice = new TODevice();
			toDevice.setActive(deviceDTO.getActive());
			toDevice.setId(deviceDTO.getId());
			toDevice.setModel(deviceDTO.getModel());
			
			to.setToDevice(toDevice);
		}
		
		UserDTO userDTO = dto.getUserDTO();
		
		if (userDTO != null) {
			TOUser toUser = new TOUser();
			toUser.setId(userDTO.getId());
			toUser.setCreationDate(userDTO.getCreationDate());
			toUser.setUpdateDate(userDTO.getUpdateDate());
			toUser.setEmail(userDTO.getEmail());
			toUser.setPassword(userDTO.getPassword());
			toUser.setType(new LabeledEnum<EnumUserType>(userDTO.getType(), getLabelUserType(userDTO.getType())));
			
			to.setToUser(toUser);
		}
		
		return to;
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
	
	public String getLabelUserType(EnumUserType type) {
		ResourceBundle bundle = FacesUtils.getResourceBundle("token_search");
		String label = null;
		
		switch (type) {
		case PERSONAL_TRAINER:
			label = bundle.getString("label_user_type_personal");
			break;
		case NUTRITIONIST:
			label = bundle.getString("label_user_type_nutri");
			break;
		case ACADEMY_MEMBER:
			label = bundle.getString("label_user_type_member");
			break;
		default:
			break;
		}
		
		return label;
	}
	
}

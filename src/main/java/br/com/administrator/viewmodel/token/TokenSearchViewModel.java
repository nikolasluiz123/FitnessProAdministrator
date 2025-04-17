package br.com.administrator.viewmodel.token;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.service.webclient.TokenWebClient;
import br.com.administrator.to.TOApplication;
import br.com.administrator.to.TODevice;
import br.com.administrator.to.TOServiceToken;
import br.com.administrator.to.TOUser;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.general.UserDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.enums.EnumServiceTokenFields;
import br.com.fitnesspro.shared.communication.query.filter.ServiceTokenFilter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class TokenSearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final TokenWebClient webClient;

	@Inject
	public TokenSearchViewModel(TokenWebClient webClient) {
        this.webClient = webClient;
	}
	
	public void invalidateToken(String tokenId) throws Exception {
		webClient.invalidateToken(tokenId);
	}
	
	public void invalidateAllTokens() throws Exception {
		webClient.invalidateAllTokens();
	}
	
	public String generateSecretKey() throws Exception {
		return webClient.getSecretKey();
	}
	
	public List<TOServiceToken> getListServiceToken(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		ServiceTokenFilter filter = getServiceTokenFilter(filterBy, sortBy);
		
		List<ServiceTokenDTO> result = webClient.getListServiceToken(filter, pageInfos);
		
		return result.stream().map(this::getServiceTokenTO).toList();
	}

	public int getCountListAcademy(Map<String, FilterMeta> filterBy) throws Exception {
		return webClient.getCountListServiceToken(getServiceTokenFilter(filterBy, null));
	}
	
	private ServiceTokenFilter getServiceTokenFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
		ServiceTokenFilter filter = new ServiceTokenFilter();
		PrimefacesFiltersUtil filterUtils = new PrimefacesFiltersUtil(filterBy);

		if (filterBy.containsKey(EnumServiceTokenFields.APPLICATION_NAME.getFieldName())) {
			filter.setApplicationName(filterUtils.getStringFilterValue(EnumServiceTokenFields.APPLICATION_NAME.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumServiceTokenFields.CREATION_DATE.getFieldName())) {
			filter.setCreationDate(filterUtils.getDateTimeRangeFilter(EnumServiceTokenFields.CREATION_DATE.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumServiceTokenFields.EXPIRATION_DATE.getFieldName())) {
			filter.setCreationDate(filterUtils.getDateTimeRangeFilter(EnumServiceTokenFields.EXPIRATION_DATE.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumServiceTokenFields.DEVICE_ID.getFieldName())) {
			filter.setDeviceId(filterUtils.getStringFilterValue(EnumServiceTokenFields.DEVICE_ID.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumServiceTokenFields.TYPE.getFieldName())) {
			filter.setTokenType(filterUtils.getEnumFilterValue(EnumServiceTokenFields.TYPE.getFieldName(), EnumTokenType.class));
		}
		
		if (filterBy.containsKey(EnumServiceTokenFields.USER_EMAIL.getFieldName())) {
			filter.setUserEmail(filterUtils.getStringFilterValue(EnumServiceTokenFields.USER_EMAIL.getFieldName()));
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			filter.setSort(filterUtils.getSortFromFieldList(sortBy, EnumServiceTokenFields.getEntries()));
		}
		
		return filter;
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

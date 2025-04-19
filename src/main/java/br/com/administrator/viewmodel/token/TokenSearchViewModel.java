package br.com.administrator.viewmodel.token;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.mappers.labeledenum.execution.TokenLabeledEnumMapper;
import br.com.administrator.mappers.token.TokenMapper;
import br.com.administrator.service.webclient.TokenWebClient;
import br.com.administrator.to.TOServiceToken;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
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
	private final TokenLabeledEnumMapper tokenLabeledEnumMapper;
	private final TokenMapper mapper;

	@Inject
	public TokenSearchViewModel(TokenWebClient webClient, TokenLabeledEnumMapper tokenLabeledEnumMapper, TokenMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
        this.tokenLabeledEnumMapper = tokenLabeledEnumMapper;
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
		
		return result.stream().map(t -> mapper.getTOServiceTokenFrom(t)).toList();
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
	
	public String getLabelTokenType(EnumTokenType type) {
		return this.tokenLabeledEnumMapper.getLabelTokenType(type);
	}
}

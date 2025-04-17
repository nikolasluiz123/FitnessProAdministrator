package br.com.administrator.viewmodel.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.service.webclient.PersonWebClient;
import br.com.administrator.to.TOPerson;
import br.com.administrator.to.TOUser;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.UserDTO;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.enums.EnumPersonFields;
import br.com.fitnesspro.shared.communication.query.filter.PersonFilter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class PersonSearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final PersonWebClient webClient;

	@Inject
	public PersonSearchViewModel(PersonWebClient webClient) {
        this.webClient = webClient;
	}
	
	public List<TOPerson> getListPerson(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		PersonFilter filter = getPersonFilter(filterBy, sortBy);
		
		List<PersonDTO> result = webClient.getListPerson(filter, pageInfos);
		
		return result.stream().map(this::getPersonTO).toList();
	}

	public int getCountListPerson(Map<String, FilterMeta> filterBy) throws Exception {
		return webClient.getCountListPerson(getPersonFilter(filterBy, null));
	}
	
	private PersonFilter getPersonFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
		PersonFilter filter = new PersonFilter();
		PrimefacesFiltersUtil filterUtil = new PrimefacesFiltersUtil(filterBy);
		
		if (filterBy.containsKey(EnumPersonFields.NAME.getFieldName())) {
			filter.setName(filterUtil.getStringFilterValue(EnumPersonFields.NAME.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumPersonFields.EMAIL.getFieldName())) {
			filter.setEmail(filterUtil.getStringFilterValue(EnumPersonFields.EMAIL.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumPersonFields.USER_TYPE.getFieldName())) {
			filter.setUserType(filterUtil.getEnumFilterValue(EnumPersonFields.USER_TYPE.getFieldName(), EnumUserType.class));
		}
		
		if (filterBy.containsKey(EnumPersonFields.CREATION_DATE.getFieldName())) {
			filter.setCreationDate(filterUtil.getDateTimeRangeFilter(EnumPersonFields.CREATION_DATE.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumPersonFields.UPDATE_DATE.getFieldName())) {
			filter.setCreationDate(filterUtil.getDateTimeRangeFilter(EnumPersonFields.UPDATE_DATE.getFieldName()));
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			filter.setSort(filterUtil.getSortFromFieldList(sortBy, EnumPersonFields.getEntries()));
		}
		
		return filter;
	}
	
	private TOPerson getPersonTO(PersonDTO dto) {
		TOPerson to = new TOPerson();
		to.setId(dto.getId());
		to.setCreationDate(dto.getCreationDate());
		to.setName(dto.getName());
		to.setUpdateDate(dto.getUpdateDate());
		to.setActive(dto.getActive());
		to.setBirthDate(dto.getBirthDate());
		to.setToUser(getTOUser(dto));
		
		return to;
	}

	private TOUser getTOUser(PersonDTO dto) {
		UserDTO userDTO = dto.getUser();
		TOUser toUser = new TOUser();
		toUser.setCreationDate(userDTO.getCreationDate());
		toUser.setEmail(userDTO.getEmail());
		toUser.setPassword(userDTO.getPassword());
		toUser.setType(new LabeledEnum<EnumUserType>(userDTO.getType(), getLabelUserType(userDTO.getType())));
		return toUser;
	}
	
	public String getLabelUserType(EnumUserType type) {
		ResourceBundle bundle = FacesUtils.getResourceBundle("lov_person");
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

package br.com.administrator.viewmodel.person;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.mappers.labeledenum.execution.UserLabeledEnumMapper;
import br.com.administrator.mappers.person.PersonMapper;
import br.com.administrator.service.webclient.PersonWebClient;
import br.com.administrator.to.TOPerson;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
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
	private final UserLabeledEnumMapper labeledEnumMapper;
	private final PersonMapper mapper;

	@Inject
	public PersonSearchViewModel(PersonWebClient webClient, UserLabeledEnumMapper labeledEnumMapper, PersonMapper mapper) {
		this.webClient = webClient;
		this.labeledEnumMapper = labeledEnumMapper;
		this.mapper = mapper;
	}

	public List<TOPerson> getListPerson(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		PersonFilter filter = getPersonFilter(filterBy, sortBy);

		List<PersonDTO> result = webClient.getListPerson(filter, pageInfos);

		return result.stream().map(p -> mapper.getTOPersonFrom(p)).toList();
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
			filter.setUserType(
					filterUtil.getEnumFilterValue(EnumPersonFields.USER_TYPE.getFieldName(), EnumUserType.class));
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

	public String getLabelUserType(EnumUserType type) {
		return labeledEnumMapper.getLabelUserType(type);
	}
}

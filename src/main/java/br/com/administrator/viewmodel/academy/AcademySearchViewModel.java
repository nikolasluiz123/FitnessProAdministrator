package br.com.administrator.viewmodel.academy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.mappers.AcademyMapper;
import br.com.administrator.service.webclient.AcademyWebClient;
import br.com.administrator.to.TOAcademy;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.enums.EnumAcademyFields;
import br.com.fitnesspro.shared.communication.query.filter.AcademyFilter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class AcademySearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final AcademyWebClient webClient;
	private final AcademyMapper academyMapper;

	@Inject
	public AcademySearchViewModel(AcademyWebClient webClient, AcademyMapper mapper) {
		this.webClient = webClient;
		this.academyMapper = mapper;
	}
	
	public List<TOAcademy> getListAcademy(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		AcademyFilter filter = getAcademyFilter(filterBy, sortBy);
		
		List<AcademyDTO> result = webClient.getListAcademy(filter, pageInfos);
		
		return result.stream().map(a -> academyMapper.getTOAcademyFrom(a)).toList();
	}

	public int getCountListAcademy(Map<String, FilterMeta> filterBy) throws Exception {
		return webClient.getCountListAcademy(getAcademyFilter(filterBy, null));
	}
	
	private AcademyFilter getAcademyFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
		AcademyFilter filter = new AcademyFilter();
		PrimefacesFiltersUtil filterUtil = new PrimefacesFiltersUtil(filterBy);
		
		if (filterBy.containsKey(EnumAcademyFields.NAME.getFieldName())) {
			filter.setName(filterUtil.getStringFilterValue(EnumAcademyFields.NAME.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumAcademyFields.ADDRESS.getFieldName())) {
			filter.setAddress(filterUtil.getStringFilterValue(EnumAcademyFields.ADDRESS.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumAcademyFields.PHONE.getFieldName())) {
			filter.setPhone(filterUtil.getStringFilterValue(EnumAcademyFields.PHONE.getFieldName()));
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			filter.setSort(filterUtil.getSortFromField(sortBy, EnumAcademyFields.getEntries()));
		}
		
		return filter;
	}
}

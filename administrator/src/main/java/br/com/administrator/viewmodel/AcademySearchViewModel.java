package br.com.administrator.viewmodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.service.webclient.AcademyWebClient;
import br.com.administrator.to.TOAcademy;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;
import br.com.fitnesspro.shared.communication.filter.AcademyFilter;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class AcademySearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final AcademyWebClient webClient;

	@Inject
	public AcademySearchViewModel(AcademyWebClient webClient) {
        this.webClient = webClient;
	}
	
	public List<TOAcademy> getListAcademy(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		AcademyFilter filter = getAcademyFilter(filterBy);
		
		List<AcademyDTO> result = webClient.getListAcademy(filter, pageInfos);
		
		return result.stream().map(this::getAcademyTO).toList();
	}

	public int getCountListAcademy(Map<String, FilterMeta> filterBy) throws Exception {
		return webClient.getCountListAcademy(getAcademyFilter(filterBy));
	}
	
	private AcademyFilter getAcademyFilter(Map<String, FilterMeta> filterBy) {
		AcademyFilter filter = new AcademyFilter();
		
		if (filterBy.containsKey("name")) {
			String filterValue = filterBy.get("name").getFilterValue().toString();
			filter.setName(filterValue);
		}
		
		if (filterBy.containsKey("address")) {
			String filterValue = filterBy.get("address").getFilterValue().toString();
			filter.setAddress(filterValue);
		}
		
		return filter;
	}
	
	private TOAcademy getAcademyTO(AcademyDTO dto) {
		TOAcademy to = new TOAcademy();
		to.setId(dto.getId());
		to.setAddress(dto.getAddress());
		to.setCreationDate(dto.getCreationDate());
		to.setName(dto.getName());
		to.setPhone(dto.getPhone());
		to.setUpdateDate(dto.getUpdateDate());
		to.setActive(dto.getActive());
		
		return to;
	}
}

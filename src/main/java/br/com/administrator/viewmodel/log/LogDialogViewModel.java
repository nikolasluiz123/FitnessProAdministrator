package br.com.administrator.viewmodel.log;

import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsPackageFields.CLIENT_EXECUTION_END;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsPackageFields.CLIENT_EXECUTION_START;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsPackageFields.SERVICE_EXECUTION_END;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsPackageFields.SERVICE_EXECUTION_START;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogPackageDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogsPackageFilter;
import br.com.fitnesspro.shared.communication.query.sort.Sort;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LogDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final ExecutionLogsWebClient webClient;
	
	@Inject
	public LogDialogViewModel(ExecutionLogsWebClient executionLogsWebClient) {
        this.webClient = executionLogsWebClient;
	}

	public List<TOExecutionLogPackage> getListExecutionLogPackage(int first, 
																  int pageSize, 
																  Map<String, SortMeta> sortBy,
																  Map<String, FilterMeta> filterBy,
																  String executionId) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		ExecutionLogsPackageFilter filter = getExecutionLogsPackageFilter(filterBy, sortBy);
		filter.setExecutionLogId(executionId);
		
		List<ExecutionLogPackageDTO> result = webClient.getListExecutionLogPackage(filter, pageInfos);
		
		return result.stream().map(this::getExecutionLogPackageTO).toList();
	}
	
	private ExecutionLogsPackageFilter getExecutionLogsPackageFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
		ExecutionLogsPackageFilter filter = new ExecutionLogsPackageFilter();
		
		PrimefacesFiltersUtil filterUtils = new PrimefacesFiltersUtil(filterBy);
		
		if (filterBy.containsKey(SERVICE_EXECUTION_START.getFieldName())) {
			filter.setServiceExecutionStart(filterUtils.getDateTimeRangeFilter(SERVICE_EXECUTION_START.getFieldName()));
		}
		
		if (filterBy.containsKey(SERVICE_EXECUTION_END.getFieldName())) {
			filter.setServiceExecutionStart(filterUtils.getDateTimeRangeFilter(SERVICE_EXECUTION_END.getFieldName()));
		}
		
		if (filterBy.containsKey(CLIENT_EXECUTION_START.getFieldName())) {
			filter.setServiceExecutionStart(filterUtils.getDateTimeRangeFilter(CLIENT_EXECUTION_START.getFieldName()));
		}
		
		if (filterBy.containsKey(CLIENT_EXECUTION_END.getFieldName())) {
			filter.setServiceExecutionStart(filterUtils.getDateTimeRangeFilter(CLIENT_EXECUTION_END.getFieldName()));
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			SortMeta sortMeta = sortBy.values().stream().findFirst().get();
			filter.setSort(new Sort(sortMeta.getField(), sortMeta.getOrder() == SortOrder.ASCENDING));
		} else {
			filter.setSort(new Sort(SERVICE_EXECUTION_START.getFieldName(), false));
		}
		
		return filter;
	}
	
	private TOExecutionLogPackage getExecutionLogPackageTO(ExecutionLogPackageDTO dto) {
		TOExecutionLogPackage to = new TOExecutionLogPackage();
		to.setAllItemsCount(dto.getAllItemsCount());
		to.setClientExecutionEnd(dto.getClientExecutionEnd());
		to.setClientExecutionStart(dto.getClientExecutionStart());
		to.setError(dto.getError());
		to.setId(dto.getId());
		to.setInsertedItemsCount(dto.getInsertedItemsCount());
		to.setRequestBody(dto.getRequestBody());
		to.setServiceExecutionEnd(dto.getServiceExecutionEnd());
		to.setServiceExecutionStart(dto.getServiceExecutionStart());
		to.setUpdatedItemsCount(dto.getUpdatedItemsCount());
		
		return to;
	}
	

	public int getCountListExecutionLogPackage(Map<String, FilterMeta> filterBy, String executionId) throws Exception {
		ExecutionLogsPackageFilter filter = getExecutionLogsPackageFilter(filterBy, null);
		filter.setExecutionLogId(executionId);
		
		return webClient.getCountListExecutionLogPackage(filter);
	}

}

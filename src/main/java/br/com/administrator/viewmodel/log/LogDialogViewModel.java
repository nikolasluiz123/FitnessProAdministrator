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

import br.com.administrator.mappers.log.LogMapper;
import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogPackageDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsPackageFields;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogsPackageFilter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LogDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final ExecutionLogsWebClient webClient;
	private final LogMapper logMapper;
	
	@Inject
	public LogDialogViewModel(ExecutionLogsWebClient executionLogsWebClient, LogMapper logMapper) {
        this.webClient = executionLogsWebClient;
        this.logMapper = logMapper;
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
		
		return result.stream().map(e -> logMapper.getTOExecutionLogPackageFrom(e)).toList();
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
			filter.setSort(filterUtils.getSortFromField(sortBy, EnumExecutionLogsPackageFields.getEntries()));
		}
		
		return filter;
	}

	public int getCountListExecutionLogPackage(Map<String, FilterMeta> filterBy, String executionId) throws Exception {
		ExecutionLogsPackageFilter filter = getExecutionLogsPackageFilter(filterBy, null);
		filter.setExecutionLogId(executionId);
		
		return webClient.getCountListExecutionLogPackage(filter);
	}

}

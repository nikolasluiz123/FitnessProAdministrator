package br.com.administrator.viewmodel.log;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.filter.ExecutionLogsFilter;
import br.com.fitnesspro.shared.communication.filter.Sort;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ExecutionLogsViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ExecutionLogsWebClient webClient;

	@Inject
	public ExecutionLogsViewModel(ExecutionLogsWebClient executionLogsWebClient) {
        this.webClient = executionLogsWebClient;
	}

	public List<TOExecutionLog> getListExecutionLog(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		ExecutionLogsFilter filter = getExecutionLogsFilter(filterBy, sortBy);
		
		List<ExecutionLogDTO> result = webClient.getListExecutionLog(filter, pageInfos);
		
		return result.stream().map(this::getExecutionLogTO).toList();
	}

	public int getCountListExecutionLog(Map<String, FilterMeta> filterBy) throws Exception {
		return webClient.getCountListExecutionLog(getExecutionLogsFilter(filterBy, null));
	}
	
	private ExecutionLogsFilter getExecutionLogsFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
		ExecutionLogsFilter filter = new ExecutionLogsFilter();
		PrimefacesFiltersUtil filterUtils = new PrimefacesFiltersUtil(filterBy);
		
		if (filterBy.containsKey("type.value")) {
			filter.setType(filterUtils.getEnumFilterValue("type.value", EnumExecutionType.class));
		}
		
		if (filterBy.containsKey("state.value")) {
			filter.setState(filterUtils.getEnumFilterValue("state.value", EnumExecutionState.class));
		}
		
		if (filterBy.containsKey("serviceExecutionStart")) {
			filter.setServiceExecutionStart(filterUtils.getDateTimeRangeFilter("serviceExecutionStart"));
		}
		
		if (filterBy.containsKey("serviceExecutionEnd")) {
			filter.setServiceExecutionEnd(filterUtils.getDateTimeRangeFilter("serviceExecutionEnd"));
		}
		
		if (filterBy.containsKey("clientExecutionStart")) {
			filter.setClientExecutionStart(filterUtils.getDateTimeRangeFilter("clientExecutionStart"));
		}
		
		if (filterBy.containsKey("clientExecutionEnd")) {
			filter.setClientExecutionEnd(filterUtils.getDateTimeRangeFilter("clientExecutionEnd"));
		}
		
		if (filterBy.containsKey("endpoint")) {
			filter.setEndPoint(filterUtils.getStringFilterValue("endpoint"));
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			SortMeta sortMeta = sortBy.values().stream().findFirst().get();
			filter.setSort(new Sort(sortMeta.getField(), sortMeta.getOrder() == SortOrder.ASCENDING));
		} else {
			filter.setSort(new Sort("serviceExecutionStart", true));
		}
		
		return filter;
	}
	
	private TOExecutionLog getExecutionLogTO(ExecutionLogDTO dto) {
		TOExecutionLog to = new TOExecutionLog();
		to.setEndpoint(dto.getEndPoint());
		to.setError(dto.getError());
		to.setServiceExecutionEnd(dto.getServiceExecutionEnd());
		to.setServiceExecutionStart(dto.getServiceExecutionStart());
		to.setClientExecutionStart(dto.getClientExecutionStart());
		to.setClientExecutionEnd(dto.getClientExecutionEnd());
		to.setId(dto.getId());
		to.setRequestBody(dto.getRequestBody());
		to.setType(new LabeledEnum<EnumExecutionType>(dto.getType(), getLabelExecutionType(dto.getType())));
		to.setState(new LabeledEnum<EnumExecutionState>(dto.getState(), getLabelExecutionState(dto.getState())));
		to.setMethodName(dto.getMethodName());
		
		return to;
	}
	
	public String getLabelExecutionType(EnumExecutionType type) {
		String label = null;
		
		switch(type) {
		case DELETE:
			label = "HTTP DELETE";
			break;
		case EXPORTATION:
			label = "Exportação";
			break;
		case GET:
			label = "HTTP GET";
			break;
		case IMPORTATION:
			label = "Importação";
			break;
		case POST:
			label = "HTTP POST";
			break;
		case PUT:
			label = "HTTP PUT";
			break;
		default:
			break;
		}
		
		return label;
	}
	
	public String getLabelExecutionState(EnumExecutionState state) {
		String label = null;
		
		switch(state) {
		case ERROR:
			label = "Erro na Execução";
			break;
		case FINISHED:
			label = "Finalizado";
			break;
		case PENDING:
			label = "Pendente";
			break;
		case RUNNING:
			label = "Executando";
			break;
		default:
			break;
		}
		
		return label;
	}

}

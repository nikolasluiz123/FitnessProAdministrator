package br.com.administrator.viewmodel.log;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLog;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.filter.ExecutionLogsFilter;
import br.com.fitnesspro.shared.communication.filter.Sort;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import kotlin.Pair;

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
	
	@SuppressWarnings("unchecked")
	private ExecutionLogsFilter getExecutionLogsFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
		ExecutionLogsFilter filter = new ExecutionLogsFilter();
		
		if (filterBy.containsKey("type.value")) {
			String filterValue = filterBy.get("type.value").getFilterValue().toString();
			filter.setType(EnumExecutionType.valueOf(filterValue));
		}
		
		if (filterBy.containsKey("state.value")) {
			String filterValue = filterBy.get("state.value").getFilterValue().toString();
			filter.setState(EnumExecutionState.valueOf(filterValue));
		}
		
		if (filterBy.containsKey("executionStart")) {
			List<LocalDate> filterValue = (List<LocalDate>) filterBy.get("executionStart").getFilterValue();
			
			LocalDateTime start = LocalDateTime.of(filterValue.get(0), LocalTime.of(0, 0));
			LocalDateTime end = LocalDateTime.of(filterValue.get(1), LocalTime.of(23, 59));
			filter.setExecutionStart(new Pair<LocalDateTime, LocalDateTime>(start, end));
		}
		
		if (filterBy.containsKey("executionEnd")) {
			List<LocalDate> filterValue = (List<LocalDate>) filterBy.get("executionEnd").getFilterValue();
			
			LocalDateTime start = LocalDateTime.of(filterValue.get(0), LocalTime.of(0, 0));
			LocalDateTime end = LocalDateTime.of(filterValue.get(1), LocalTime.of(23, 59));
			filter.setExecutionEnd(new Pair<LocalDateTime, LocalDateTime>(start, end));
		}
		
		if (filterBy.containsKey("endpoint")) {
			String filterValue = filterBy.get("endpoint").getFilterValue().toString();
			filter.setEndPoint(filterValue);
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			SortMeta sortMeta = sortBy.values().stream().findFirst().get();
			filter.setSort(new Sort(sortMeta.getField(), sortMeta.getOrder() == SortOrder.ASCENDING));
		} else {
			filter.setSort(new Sort("executionStart", true));
		}
		
		return filter;
	}
	
	private TOExecutionLog getExecutionLogTO(ExecutionLogDTO dto) {
		TOExecutionLog to = new TOExecutionLog();
		to.setEndpoint(dto.getEndPoint());
		to.setError(dto.getError());
		to.setExecutionEnd(dto.getExecutionEnd());
		to.setExecutionStart(dto.getExecutionStart());
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

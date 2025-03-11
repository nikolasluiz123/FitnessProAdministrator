package br.com.administrator.viewmodel.log;

import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.END_POINT;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.EXECUTION_STATE;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.EXECUTION_TYPE;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.METHOD_NAME;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.USER_EMAIL;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogsFilter;
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
		
		if (filterBy.containsKey(EXECUTION_TYPE.getFieldName())) {
			filter.setType(filterUtils.getEnumFilterValue(EXECUTION_TYPE.getFieldName(), EnumExecutionType.class));
		}
		
		if (filterBy.containsKey(EXECUTION_STATE.getFieldName())) {
			filter.setState(filterUtils.getEnumFilterValue(EXECUTION_STATE.getFieldName(), EnumExecutionState.class));
		}
		
		if (filterBy.containsKey(END_POINT.getFieldName())) {
			filter.setEndPoint(filterUtils.getStringFilterValue(END_POINT.getFieldName()));
		}
		
		if (filterBy.containsKey(METHOD_NAME.getFieldName())) {
			filter.setEndPoint(filterUtils.getStringFilterValue(METHOD_NAME.getFieldName()));
		}
		
		if (filterBy.containsKey(USER_EMAIL.getFieldName())) {
			filter.setEndPoint(filterUtils.getStringFilterValue(USER_EMAIL.getFieldName()));
		}
		
		return filter;
	}
	
	private TOExecutionLog getExecutionLogTO(ExecutionLogDTO dto) {
		TOExecutionLog to = new TOExecutionLog();
		to.setEndpoint(dto.getEndPoint());
		to.setId(dto.getId());
		to.setType(new LabeledEnum<EnumExecutionType>(dto.getType(), getLabelExecutionType(dto.getType())));
		to.setState(new LabeledEnum<EnumExecutionState>(dto.getState(), getLabelExecutionState(dto.getState())));
		to.setMethodName(dto.getMethodName());
		to.setLastUpdateDate(dto.getLastUpdateDate());
		to.setPageSize(dto.getPageSize());
		to.setUserEmail(dto.getUserEmail());
		
		return to;
	}
	
	public String getLabelExecutionType(EnumExecutionType type) {
		ResourceBundle bundle = FacesUtils.getResourceBundle("execution_logs");
		String label = null;
		
		switch(type) {
		case DELETE:
			label = bundle.getString("label_execution_type_delete");
			break;
		case EXPORTATION:
			label = bundle.getString("label_execution_type_exportation");
			break;
		case GET:
			label = bundle.getString("label_execution_type_get");
			break;
		case IMPORTATION:
			label = bundle.getString("label_execution_type_importation");
			break;
		case POST:
			label = bundle.getString("label_execution_type_post");
			break;
		case PUT:
			label = bundle.getString("label_execution_type_put");
			break;
		default:
			break;
		}
		
		return label;
	}
	
	public String getLabelExecutionState(EnumExecutionState state) {
		ResourceBundle bundle = FacesUtils.getResourceBundle("execution_logs");
		String label = null;
		
		switch(state) {
		case ERROR:
			label = bundle.getString("label_execution_state_error");
			break;
		case FINISHED:
			label = bundle.getString("label_execution_state_finished");
			break;
		case PENDING:
			label = bundle.getString("label_execution_state_pending");
			break;
		case RUNNING:
			label = bundle.getString("label_execution_state_running");
			break;
		default:
			break;
		}
		
		return label;
	}

}

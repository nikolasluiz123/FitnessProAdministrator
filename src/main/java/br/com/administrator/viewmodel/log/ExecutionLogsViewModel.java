package br.com.administrator.viewmodel.log;

import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.END_POINT;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.EXECUTION_STATE;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.EXECUTION_TYPE;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.METHOD_NAME;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.USER_EMAIL;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.mappers.labeledenum.execution.ExecutionLabeledEnumMapper;
import br.com.administrator.mappers.log.LogMapper;
import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogsFilter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ExecutionLogsViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ExecutionLogsWebClient webClient;
	private final ExecutionLabeledEnumMapper labeledEnumMapper;
	private final LogMapper logMapper;

	@Inject
	public ExecutionLogsViewModel(ExecutionLogsWebClient executionLogsWebClient, ExecutionLabeledEnumMapper labeledEnumMapper, LogMapper logMapper) {
        this.webClient = executionLogsWebClient;
        this.labeledEnumMapper = labeledEnumMapper;
        this.logMapper = logMapper;
	}

	public List<TOExecutionLog> getListExecutionLog(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		ExecutionLogsFilter filter = getExecutionLogsFilter(filterBy, sortBy);
		
		List<ExecutionLogDTO> result = webClient.getListExecutionLog(filter, pageInfos);
		
		return result.stream().map(e -> logMapper.getTOExecutionLogFrom(e)).toList();
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
	
	public String getLabelExecutionType(EnumExecutionType type) {
		return labeledEnumMapper.getLabelExecutionType(type);
	}
	
	public String getLabelExecutionState(EnumExecutionState state) {
		return labeledEnumMapper.getLabelExecutionState(state);
	}
}

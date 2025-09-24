package br.com.administrator.viewmodel.log;

import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.APPLICATION_NAME;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.CREATION_DATE;
import static br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields.DEVICE_ID;
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

import br.com.administrator.managedbean.executionlogs.enums.EnumThreeOptions;
import br.com.administrator.mappers.labeledenum.ThreeOptionsLabeledEnumMapper;
import br.com.administrator.mappers.labeledenum.execution.ExecutionLabeledEnumMapper;
import br.com.administrator.mappers.log.LogMapper;
import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogsFields;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogsFilter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@ViewScoped
public class ExecutionLogsViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ExecutionLogsWebClient webClient;
	
	@Inject
	private ExecutionLabeledEnumMapper labeledEnumMapper;
	
	@Inject
	private ThreeOptionsLabeledEnumMapper threeOptionsEnumMapper;
	
	@Inject
	private LogMapper logMapper;
	
	private EnumThreeOptions showExecutionsWithInsertedEntities;
	private EnumThreeOptions showExecutionsWithUpdatedEntities;

	public ExecutionLogsViewModel() {
        if (this.showExecutionsWithInsertedEntities == null) {
        	this.showExecutionsWithInsertedEntities = EnumThreeOptions.ALL;
        }
        
        if (this.showExecutionsWithUpdatedEntities == null) {
        	this.showExecutionsWithUpdatedEntities = EnumThreeOptions.ALL;
        }
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
			filter.setMethodName(filterUtils.getStringFilterValue(METHOD_NAME.getFieldName()));
		}
		
		if (filterBy.containsKey(USER_EMAIL.getFieldName())) {
			filter.setUserEmail(filterUtils.getStringFilterValue(USER_EMAIL.getFieldName()));
		}
		
		if (filterBy.containsKey(DEVICE_ID.getFieldName())) {
			filter.setDeviceId(filterUtils.getStringFilterValue(DEVICE_ID.getFieldName()));
		}
		
		if (filterBy.containsKey(APPLICATION_NAME.getFieldName())) {
			filter.setApplicationName(filterUtils.getStringFilterValue(APPLICATION_NAME.getFieldName()));
		}
		
		if (filterBy.containsKey(CREATION_DATE.getFieldName())) {
			filter.setCreationDate(filterUtils.getDateTimeRangeFilter(CREATION_DATE.getFieldName()));
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			filter.setSort(filterUtils.getSortFromFieldList(sortBy, EnumExecutionLogsFields.getEntries()));
		}
		
		switch (this.showExecutionsWithInsertedEntities) {
			case YES -> filter.setExecutionsWithInsertedEntities(true);
			case NO -> filter.setExecutionsWithInsertedEntities(false);
			case ALL -> filter.setExecutionsWithInsertedEntities(null);
		}
		
		switch (this.showExecutionsWithUpdatedEntities) {
			case YES -> filter.setExecutionsWithUpdatedEntities(true);
			case NO -> filter.setExecutionsWithUpdatedEntities(false);
			case ALL -> filter.setExecutionsWithUpdatedEntities(null);
		}

		return filter;
	}
	
	public String getLabelExecutionType(EnumExecutionType type) {
		return labeledEnumMapper.getLabelExecutionType(type);
	}
	
	public String getLabelExecutionState(EnumExecutionState state) {
		return labeledEnumMapper.getLabelExecutionState(state);
	}
	
	public String getLabelThreeOption(EnumThreeOptions option) {
		return threeOptionsEnumMapper.getLabelThreeOptions(option);
	}

	public void setShowExecutionsWithInsertedEntities(EnumThreeOptions showExecutionsWithInsertedEntities) {
		this.showExecutionsWithInsertedEntities = showExecutionsWithInsertedEntities;
	}

	public void setShowExecutionsWithUpdatedEntities(EnumThreeOptions showExecutionsWithUpdatedEntities) {
		this.showExecutionsWithUpdatedEntities = showExecutionsWithUpdatedEntities;
	}
	
}

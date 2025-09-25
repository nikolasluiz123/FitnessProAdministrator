package br.com.administrator.viewmodel.log;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.mappers.log.LogMapper;
import br.com.administrator.service.webclient.ExecutionLogsWebClient;
import br.com.administrator.to.TOExecutionLogSubPackage;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogSubPackageDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.enums.EnumExecutionLogSubPackageFields;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogSubPackageFilter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LogPackageDialogViewModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ExecutionLogsWebClient webClient;
    private final LogMapper logMapper;

    @Inject
    public LogPackageDialogViewModel(ExecutionLogsWebClient executionLogsWebClient, LogMapper logMapper) {
        this.webClient = executionLogsWebClient;
        this.logMapper = logMapper;
    }

    public List<TOExecutionLogSubPackage> getListExecutionLogSubPackage(int first,
                                                                          int pageSize,
                                                                          Map<String, SortMeta> sortBy,
                                                                          Map<String, FilterMeta> filterBy,
                                                                          String executionPackageId) throws Exception {
        CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
        ExecutionLogSubPackageFilter filter = getExecutionLogSubPackageFilter(filterBy, sortBy);
        filter.setExecutionLogPackageId(executionPackageId);

        List<ExecutionLogSubPackageDTO> result = webClient.getListExecutionLogSubPackage(filter, pageInfos);

        return result.stream().map(logMapper::getTOExecutionLogSubPackageFrom).toList();
    }

    public int getCountListExecutionLogSubPackage(Map<String, FilterMeta> filterBy, String executionPackageId) throws Exception {
        ExecutionLogSubPackageFilter filter = getExecutionLogSubPackageFilter(filterBy, null);
        filter.setExecutionLogPackageId(executionPackageId);

        return webClient.getCountListExecutionLogSubPackage(filter);
    }

    private ExecutionLogSubPackageFilter getExecutionLogSubPackageFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
        ExecutionLogSubPackageFilter filter = new ExecutionLogSubPackageFilter();
        PrimefacesFiltersUtil filterUtils = new PrimefacesFiltersUtil(filterBy);

        if (filterBy.containsKey(EnumExecutionLogSubPackageFields.ENTITY_NAME.getFieldName())) {
            filter.setEntityName(filterUtils.getStringFilterValue(EnumExecutionLogSubPackageFields.ENTITY_NAME.getFieldName()));
        }

        if (sortBy != null && !sortBy.values().isEmpty()) {
            filter.setSort(filterUtils.getSortFromFieldList(sortBy, EnumExecutionLogSubPackageFields.getEntries()));
        }

        return filter;
    }
}
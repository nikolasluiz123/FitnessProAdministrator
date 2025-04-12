package br.com.administrator.managedbean.executionlogs;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.viewmodel.log.ExecutionLogsViewModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LazyExecutionLogDataModel extends AbstractLazyDataModel<TOExecutionLog> {

	private static final long serialVersionUID = 1L;
	
	private final ExecutionLogsViewModel viewModel;
	
	public LazyExecutionLogDataModel() {
		this.viewModel = null;
	}
	
	@Inject
	public LazyExecutionLogDataModel(ExecutionLogsViewModel webClient) {
		this.viewModel = webClient;
	}

	@Override
	public int onCount(Map<String, FilterMeta> filterBy) throws Exception {
		return viewModel.getCountListExecutionLog(filterBy);
	}

	@Override
	public List<TOExecutionLog> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		return viewModel.getListExecutionLog(first, pageSize, sortBy, filterBy);
	}

}

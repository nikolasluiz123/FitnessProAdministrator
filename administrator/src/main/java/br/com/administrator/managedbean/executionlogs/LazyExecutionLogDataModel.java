package br.com.administrator.managedbean.executionlogs;

import java.util.ArrayList;
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
	public int count(Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getCountListExecutionLog(filterBy);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<TOExecutionLog> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getListExecutionLog(first, pageSize, sortBy, filterBy);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}

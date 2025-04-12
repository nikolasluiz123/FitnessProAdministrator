package br.com.administrator.managedbean.executionlogs;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.administrator.viewmodel.log.LogDialogViewModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LazyExecutionLogPackageDataModel extends AbstractLazyDataModel<TOExecutionLogPackage> {

	private static final long serialVersionUID = 1L;
	
	private final LogDialogViewModel viewModel;
	private String executionLogId;
	
	public LazyExecutionLogPackageDataModel() {
		this.viewModel = null;
	}
	
	@Inject
	public LazyExecutionLogPackageDataModel(LogDialogViewModel viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public int onCount(Map<String, FilterMeta> filterBy) throws Exception {
		return viewModel.getCountListExecutionLogPackage(filterBy, executionLogId);
	}

	@Override
	public List<TOExecutionLogPackage> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		return viewModel.getListExecutionLogPackage(first, pageSize, sortBy, filterBy, executionLogId);
	}

	public String getExecutionLogId() {
		return executionLogId;
	}

	public void setExecutionLogId(String executionLogId) {
		this.executionLogId = executionLogId;
	}
	
}

package br.com.administrator.managedbean.executionlogs;

import java.util.ArrayList;
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
	public int count(Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getCountListExecutionLogPackage(filterBy, executionLogId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<TOExecutionLogPackage> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getListExecutionLogPackage(first, pageSize, sortBy, filterBy, executionLogId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String getExecutionLogId() {
		return executionLogId;
	}

	public void setExecutionLogId(String executionLogId) {
		this.executionLogId = executionLogId;
	}
	
}

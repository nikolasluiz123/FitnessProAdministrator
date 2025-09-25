package br.com.administrator.managedbean.executionlogs.lazymodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.to.TOExecutionLogSubPackage;
import br.com.administrator.viewmodel.log.LogPackageDialogViewModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LazyExecutionLogSubPackageDataModel extends AbstractLazyDataModel<TOExecutionLogSubPackage> {

    private static final long serialVersionUID = 1L;

    private final LogPackageDialogViewModel viewModel;
    private String executionLogPackageId;

    public LazyExecutionLogSubPackageDataModel() {
        this.viewModel = null;
    }

    @Inject
    public LazyExecutionLogSubPackageDataModel(LogPackageDialogViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected List<TOExecutionLogSubPackage> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
        return viewModel.getListExecutionLogSubPackage(first, pageSize, sortBy, filterBy, executionLogPackageId);
    }

    @Override
    protected int onCount(Map<String, FilterMeta> filterBy) throws Exception {
        return viewModel.getCountListExecutionLogSubPackage(filterBy, executionLogPackageId);
    }

    public String getExecutionLogPackageId() {
        return executionLogPackageId;
    }

    public void setExecutionLogPackageId(String executionLogPackageId) {
        this.executionLogPackageId = executionLogPackageId;
    }
}
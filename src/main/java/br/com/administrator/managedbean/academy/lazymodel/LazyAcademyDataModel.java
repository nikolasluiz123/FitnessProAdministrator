package br.com.administrator.managedbean.academy.lazymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.to.TOAcademy;
import br.com.administrator.viewmodel.academy.AcademySearchViewModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LazyAcademyDataModel extends AbstractLazyDataModel<TOAcademy> {

	private static final long serialVersionUID = 1L;

	private final AcademySearchViewModel viewModel;
	
	public LazyAcademyDataModel() {
		this.viewModel = null;
	}
	
	@Inject
	public LazyAcademyDataModel(AcademySearchViewModel webClient) {
		this.viewModel = webClient;
	}

	
	@Override
	protected List<TOAcademy> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getListAcademy(first, pageSize, sortBy, filterBy);
		} catch (Exception e) {
			this.callback.onException(e);
			return new ArrayList<>();
		}
	}

	@Override
	protected int onCount(Map<String, FilterMeta> filterBy) throws Exception {
		return viewModel.getCountListAcademy(filterBy);
	}

}

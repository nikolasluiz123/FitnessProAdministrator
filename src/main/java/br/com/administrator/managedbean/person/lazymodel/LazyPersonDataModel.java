package br.com.administrator.managedbean.person.lazymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.to.TOPerson;
import br.com.administrator.viewmodel.person.PersonSearchViewModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LazyPersonDataModel extends AbstractLazyDataModel<TOPerson> {

	private static final long serialVersionUID = 1L;

	private final PersonSearchViewModel viewModel;
	
	public LazyPersonDataModel() {
		this.viewModel = null;
	}
	
	@Inject
	public LazyPersonDataModel(PersonSearchViewModel viewModel) {
		this.viewModel = viewModel;
	}

	
	@Override
	protected List<TOPerson> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getListPerson(first, pageSize, sortBy, filterBy);
		} catch (Exception e) {
			this.callback.onException(e);
			return new ArrayList<>();
		}
	}

	@Override
	protected int onCount(Map<String, FilterMeta> filterBy) throws Exception {
		return viewModel.getCountListPerson(filterBy);
	}

}

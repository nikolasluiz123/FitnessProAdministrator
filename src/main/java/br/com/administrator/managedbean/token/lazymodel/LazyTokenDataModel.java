package br.com.administrator.managedbean.token.lazymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.to.TOServiceToken;
import br.com.administrator.viewmodel.token.TokenSearchViewModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LazyTokenDataModel extends AbstractLazyDataModel<TOServiceToken> {

	private static final long serialVersionUID = 1L;

	private final TokenSearchViewModel viewModel;
	
	public LazyTokenDataModel() {
		this.viewModel = null;
	}
	
	@Inject
	public LazyTokenDataModel(TokenSearchViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	@Override
	protected List<TOServiceToken> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getListServiceToken(first, pageSize, sortBy, filterBy);
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

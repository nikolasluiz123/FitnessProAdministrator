package br.com.administrator.managedbean.device.lazymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.to.TODevice;
import br.com.administrator.viewmodel.device.DeviceSearchViewModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LazyDeviceDataModel extends AbstractLazyDataModel<TODevice> {

	private static final long serialVersionUID = 1L;

	private final DeviceSearchViewModel viewModel;
	
	public LazyDeviceDataModel() {
		this.viewModel = null;
	}
	
	@Inject
	public LazyDeviceDataModel(DeviceSearchViewModel viewModel) {
		this.viewModel = viewModel;
	}

	
	@Override
	protected List<TODevice> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		try {
			return viewModel.getListDevice(first, pageSize, sortBy, filterBy);
		} catch (Exception e) {
			this.callback.onException(e);
			return new ArrayList<>();
		}
	}

	@Override
	protected int onCount(Map<String, FilterMeta> filterBy) throws Exception {
		return viewModel.getCountListDevice(filterBy);
	}

}

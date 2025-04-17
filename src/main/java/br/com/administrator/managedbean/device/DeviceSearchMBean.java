package br.com.administrator.managedbean.device;

import br.com.administrator.managedbean.common.beans.AbstractSearchMBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("deviceSearchMBean")
@ViewScoped
public class DeviceSearchMBean extends AbstractSearchMBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyDeviceDataModel lazyModel;

	@PostConstruct
	public void init() {
		this.lazyModel.setCallback(new DefaultLazyDataModelCallback());
	}
	
	public LazyDeviceDataModel getLazyModel() {
		return lazyModel;
	}

	public void onRequestReloadDatatable() {
		lazyModel.reloadPreservingPagingState();
	}

	@Override
	protected String getBundleFileName() {
		return "device_search";
	}

}

package br.com.administrator.managedbean.lov;

import br.com.administrator.managedbean.common.beans.AbstractPagingLovMBean;
import br.com.administrator.managedbean.device.LazyDeviceDataModel;
import br.com.administrator.to.TODevice;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("lovDeviceMBean")
@ViewScoped
public class LovDeviceMBean extends AbstractPagingLovMBean<TODevice> {

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
		return "lov_device";
	}

}

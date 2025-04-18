package br.com.administrator.managedbean.device;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.to.TODevice;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("deviceSearchMBean")
@ViewScoped
public class DeviceSearchMBean extends AbstractPagingSearchMBean<TODevice, LazyDeviceDataModel> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyDeviceDataModel lazyModel;

	@Override
	public LazyDeviceDataModel getLazyModel() {
		return lazyModel;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "device_search";
	}

}

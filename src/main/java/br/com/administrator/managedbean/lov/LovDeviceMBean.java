package br.com.administrator.managedbean.lov;

import br.com.administrator.managedbean.common.beans.AbstractPagingLovMBean;
import br.com.administrator.managedbean.device.lazymodel.LazyDeviceDataModel;
import br.com.administrator.to.TODevice;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("lovDeviceMBean")
@ViewScoped
public class LovDeviceMBean extends AbstractPagingLovMBean<TODevice, LazyDeviceDataModel> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyDeviceDataModel lazyModel;

	@Override
	public LazyDeviceDataModel getLazyModel() {
		return lazyModel;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.lovs.lov_device";
	}

}

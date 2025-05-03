package br.com.administrator.managedbean.device;

import java.util.List;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.managedbean.device.lazymodel.LazyDeviceDataModel;
import br.com.administrator.managedbean.notification.NotificationDialogMBean;
import br.com.administrator.to.TODevice;
import br.com.administrator.to.TONotification;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("deviceSearchMBean")
@ViewScoped
public class DeviceSearchMBean extends AbstractPagingSearchMBean<TODevice, LazyDeviceDataModel> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyDeviceDataModel lazyModel;
	
	@Inject
	private NotificationDialogMBean notificationMBean;
	
	public void onNotifyDeviceClick(TODevice toDevice) {
		TONotification to = new TONotification();
		to.setDevicesIds(List.of(toDevice.getId()));
		
		notificationMBean.init(to);
	}
	
	@Override
	public LazyDeviceDataModel getLazyModel() {
		return lazyModel;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.device.device_search";
	}

}

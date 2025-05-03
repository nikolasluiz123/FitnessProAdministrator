package br.com.administrator.managedbean.notification;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TONotification;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.notification.NotificationDialogViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("notificationDialogMBean")
@ViewScoped
public class NotificationDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TONotification toNotification;

	@Inject
	private NotificationDialogViewModel viewModel;

	@PostConstruct
	public void init() {
		this.toNotification = new TONotification();
	}
	
	public void init(TONotification to) {
		this.toNotification = to;
	}

	public void onNotifyClick() {
		try {
			viewModel.sendNotification(this.toNotification);

			FacesUtils.addSucccessMessage("notificationDialogMessages", getScreenBundleString("notify_send_success"),
					getScreenBundleString("notify_success_summary"));
		} catch (ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("notify_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getScreenBundleString("notify_error_summary"));
		}
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.notification.notification_dialog";
	}

	public TONotification getToNotification() {
		return toNotification;
	}

	public void setToNotification(TONotification toNotification) {
		this.toNotification = toNotification;
	}

}

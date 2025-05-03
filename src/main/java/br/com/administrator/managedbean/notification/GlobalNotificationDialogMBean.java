package br.com.administrator.managedbean.notification;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOGlobalNotification;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.notification.NotificationDialogViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("globalNotificationDialogMBean")
@ViewScoped
public class GlobalNotificationDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOGlobalNotification toNotification;

	@Inject
	private NotificationDialogViewModel viewModel;

	@PostConstruct
	public void init() {
		this.toNotification = new TOGlobalNotification();
	}

	public void onNotifyClick() {
		try {
			viewModel.sendGlobalNotification(this.toNotification);

			FacesUtils.addSucccessMessage("globalNotificationDialogMessages", getScreenBundleString("notify_send_success"),
					getScreenBundleString("notify_success_summary"));
		} catch (ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler("globalNotificationDialogMessages", exception, getScreenBundleString("notify_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler("globalNotificationDialogMessages", exception, getScreenBundleString("notify_error_summary"));
		}
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.notification.global_notification_dialog";
	}

	public TOGlobalNotification getToNotification() {
		return toNotification;
	}

	public void setToNotification(TOGlobalNotification toNotification) {
		this.toNotification = toNotification;
	}

}

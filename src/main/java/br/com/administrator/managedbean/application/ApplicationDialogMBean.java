package br.com.administrator.managedbean.application;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOApplication;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.application.ApplicationDialogViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("applicationDialogMBean")
@ViewScoped
public class ApplicationDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOApplication toApplication;
	
	@Inject
	private ApplicationDialogViewModel viewModel;	
	
	@PostConstruct
	public void init() {
		this.toApplication = new TOApplication();
	}
	
	public void init(TOApplication to) {
		this.toApplication = to;
	}

	public void onApplicationSaveClick() {
		try {
			viewModel.saveApplication(toApplication);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("save_application_success", toApplication.getName()), 
										  getMaintenanceBundleString("save_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
		}
	}
	
	public void onApplicationInactivateClick() {
		try {
			viewModel.inactivateApplication(toApplication);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("inactivate_application_success", toApplication.getName()), 
										  getMaintenanceBundleString("inactivation_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getMaintenanceBundleString("inactivation_error_summary"));
		}
	}
	
	public String getTitle() {
		if (toApplication.getId() != null) {
			return getScreenBundleString("label_title_application", toApplication.getName());
		} else {
			return getScreenBundleString("label_title_new_application");
		}
	}
	
	public String getMessageConfirmationInactivation() {
		return getScreenBundleString("message_dialog_confirmation_inactivation", toApplication.getName());
	}

	public Boolean getDisabledInactivateButton() {
		return getDisabledDialog() || this.toApplication.getId() == null;
	}
	
	public Boolean getDisabledDialog() {
		return this.toApplication.getId() != null && !this.toApplication.getActive();
	}

	public TOApplication getToApplication() {
		return toApplication;
	}

	public void setToApplication(TOApplication toApplication) {
		this.toApplication = toApplication;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.applicattion.application_dialog";
	}

}

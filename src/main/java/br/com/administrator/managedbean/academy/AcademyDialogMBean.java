package br.com.administrator.managedbean.academy;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOAcademy;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.academy.AcademyDialogViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("academyDialogMBean")
@ViewScoped
public class AcademyDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOAcademy toAcademy;
	
	@Inject
	private AcademyDialogViewModel viewModel;	
	
	@PostConstruct
	public void init() {
		this.toAcademy = new TOAcademy();
	}
	
	public void init(TOAcademy to) {
		this.toAcademy = to;
	}

	public void onAcademySaveClick() {
		try {
			viewModel.saveAcademy(toAcademy);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("save_academy_success", toAcademy.getName()), 
										  getMaintenanceBundleString("save_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
		}
	}
	
	public void onAcademyInactivateClick( ) {
		try {
			viewModel.inactivateAcademy(toAcademy);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("inactivate_academy_success", toAcademy.getName()), 
				  						  getMaintenanceBundleString("save_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getMaintenanceBundleString("inactivation_error_summary"));
		}
	}
	
	public String getTitle() {
		if (toAcademy.getId() != null) {
			return getScreenBundleString("label_title_academy", toAcademy.getName());
		} else {
			return getScreenBundleString("label_title_new_academy");
		}
	}
	
	public String getMessageConfirmationInactivation() {
		return getScreenBundleString("message_dialog_confirmation_inactivation", toAcademy.getName());
	}

	public TOAcademy getToAcademy() {
		return toAcademy;
	}

	public void setToAcademy(TOAcademy toAcademy) {
		this.toAcademy = toAcademy;
	}
	
	public Boolean getDisabledInactivateButton() {
		return getDisabledDialog() || this.toAcademy.getId() == null;
	}
	
	public Boolean getDisabledDialog() {
		return this.toAcademy.getId() != null && !this.toAcademy.getActive();
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.academy.academy_dialog";
	}

}

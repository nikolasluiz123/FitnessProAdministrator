package br.com.administrator.managedbean.application;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractSearchMBean;
import br.com.administrator.to.TOApplication;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.application.ApplicationSearchViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("applicationSearchMBean")
@ViewScoped
public class ApplicationSearchMBean extends AbstractSearchMBean<TOApplication> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationSearchViewModel viewModel;
	
	@Inject
	private ApplicationDialogMBean applicationDialogMBean;

	@Override
	public void onRequestReloadDatatable() {
		loadApplicationList();
	}
	
	@Override
	public void onRowSelect(SelectEvent<TOApplication> event) {
		applicationDialogMBean.init(event.getObject());
	}
	
	private void loadApplicationList() {
		try {
			setData(viewModel.getListApplication());
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getSearchBundleString("search_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getSearchBundleString("search_error_summary"));
		}
	}
	
	public void onNewApplicationClick() {
		applicationDialogMBean.init();
	}

	public void onApplicationInactivateClick(TOApplication to) {
		try {
			viewModel.inactivateApplication(to);
			FacesUtils.addSucccessMessage(getScreenBundleString("inactivate_application_success", to.getName()), 
										  getMaintenanceBundleString("inactivation_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getMaintenanceBundleString("inactivation_error_summary"));
		}
	}
	
	public String getMessageConfirmationInactivation(TOApplication to) {
		return getScreenBundleString("message_dialog_confirmation_inactivation", to.getName());
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.applicattion.application_search";
	}

}

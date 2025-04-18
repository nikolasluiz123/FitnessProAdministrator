package br.com.administrator.managedbean.application;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOApplication;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.application.ApplicationSearchViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("applicationSearchMBean")
@ViewScoped
public class ApplicationSearchMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationSearchViewModel viewModel;
	
	private List<TOApplication> applicationList;
	
	@Inject
	private ApplicationDialogMBean applicationDialogMBean;

	@PostConstruct
	public void init() {
		this.applicationList = new ArrayList<>();
		loadApplicationList();
	}
	
	private void loadApplicationList() {
		try {
			this.applicationList = viewModel.getListApplication();;
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("load_application_list_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getScreenBundleString("load_application_list_error_summary"));
		}
	}
	
	public void onNewApplicationClick() {
		applicationDialogMBean.init();
	}

	public void onRowSelect(SelectEvent<TOApplication> event) {
		applicationDialogMBean.init(event.getObject());
	}

	public void onRequestReloadDatatable() {
		loadApplicationList();
	}
	
	public void onApplicationInactivateClick(TOApplication to) {
		try {
			viewModel.inactivateApplication(to);
			FacesUtils.addSucccessMessage(getScreenBundleString("inactivate_application_success", to.getName()), getScreenBundleString("save_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getScreenBundleString("inactivation_error_summary"));
		}
	}
	
	public String getMessageConfirmationInactivation(TOApplication to) {
		return getScreenBundleString("message_dialog_confirmation_inactivation", to.getName());
	}

	public List<TOApplication> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<TOApplication> applicationList) {
		this.applicationList = applicationList;
	}

	@Override
	protected String getScreenBundleFilePath() {
		return "application_search";
	}

}

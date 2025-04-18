package br.com.administrator.managedbean.lov;

import java.util.ArrayList;
import java.util.List;

import br.com.administrator.managedbean.common.beans.AbstractLovMBean;
import br.com.administrator.to.TOApplication;
import br.com.administrator.viewmodel.application.ApplicationSearchViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("lovApplicationMBean")
@ViewScoped
public class LovApplicationMBean extends AbstractLovMBean<TOApplication> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationSearchViewModel viewModel;
	
	private List<TOApplication> applicationList;
	
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
	
	public void onRequestReloadDatatable() {
		loadApplicationList();
	}
	
	public List<TOApplication> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<TOApplication> applicationList) {
		this.applicationList = applicationList;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "lov_application";
	}

}

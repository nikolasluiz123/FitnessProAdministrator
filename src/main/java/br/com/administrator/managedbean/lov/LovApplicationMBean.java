package br.com.administrator.managedbean.lov;

import br.com.administrator.managedbean.common.beans.AbstractLovMBean;
import br.com.administrator.to.TOApplication;
import br.com.administrator.viewmodel.application.ApplicationSearchViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("lovApplicationMBean")
@ViewScoped
public class LovApplicationMBean extends AbstractLovMBean<TOApplication> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationSearchViewModel viewModel;
	
	@Override
	public void onRequestReloadDatatable() {
		loadApplicationList();
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
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.lovs.lov_application";
	}

}

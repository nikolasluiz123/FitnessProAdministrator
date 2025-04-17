package br.com.administrator.managedbean.menu;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.managedbean.common.constants.IScreensRedirect;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("menuMBean")
@SessionScoped
public class MenuMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MenuViewModel viewModel;
	
	public void onExecutionLogsClick() {
		try {
			FacesUtils.redirect(IScreensRedirect.EXECUTION_LOGS);
		} catch (Exception e) {
			navigationExceptionHandler(e);
		}
	}
	
	public void onAcademiesClick() {
		try {
			FacesUtils.redirect(IScreensRedirect.ACADEMY_SEARCH);
		} catch (Exception e) {
			navigationExceptionHandler(e);
		}
	}

	public void onCachesClick() {
		try {
			FacesUtils.redirect(IScreensRedirect.CACHES_SEARCH);
		} catch (Exception e) {
			navigationExceptionHandler(e);
		}
	}
	
	public void onApplicationsClick() {
		try {
			FacesUtils.redirect(IScreensRedirect.APPLICATIONS);
		} catch (Exception e) {
			navigationExceptionHandler(e);
		}
	}
	
	public void onDevicesClick() {
		try {
			FacesUtils.redirect(IScreensRedirect.DEVICES);
		} catch (Exception e) {
			navigationExceptionHandler(e);
		}
	}
	
	public void onTokensClick() {
		try {
			FacesUtils.redirect(IScreensRedirect.TOKENS);
		} catch (Exception e) {
			navigationExceptionHandler(e);
		}
	}
	
	public void onLogout() {
		try {
			viewModel.logout();
			FacesUtils.redirect(IScreensRedirect.LOGIN);
		} catch (ExpiredTokenException e) {
			// Ignorada pois vai deslogar
		} catch (Exception e) {
			exceptionHandler(e, getBundle().getString("logout_error_summary"));
		}
	}

	private void navigationExceptionHandler(Exception e) {
		exceptionHandler(e, getBundle().getString("navigation_error_summary"));
	}
	
	@Override
	protected String getBundleFileName() {
		return "menu";
	}

}

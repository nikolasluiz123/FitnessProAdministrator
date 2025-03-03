package br.com.administrator.managedbean.menu;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.managedbean.common.constants.IScreensRedirect;
import br.com.administrator.utils.FacesUtils;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("menuMBean")
@SessionScoped
public class MenuMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;
	
	public void onExecutionLogsClick() {
		try {
			FacesUtils.redirect(IScreensRedirect.EXECUTION_LOGS);
		} catch (Exception e) {
			navigationExceptionHandler(e);
		}
	}
	
	public void onUsersClick() {
		try {
			
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


	private void navigationExceptionHandler(Exception e) {
		exceptionHandler(e, getBundle().getString("navigation_error_summary"));
	}
	
	@Override
	protected String getBundleFileName() {
		return "menu";
	}

}

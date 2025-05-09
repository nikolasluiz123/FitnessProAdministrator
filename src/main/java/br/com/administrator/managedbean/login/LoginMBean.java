package br.com.administrator.managedbean.login;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.managedbean.common.constants.IScreensRedirect;
import br.com.administrator.to.TOLogin;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.login.LoginViewModel;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("loginMBean")
@ViewScoped
public class LoginMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOLogin toLogin;
	
	@Inject
	private LoginViewModel loginViewModel;
	
	@PostConstruct
	public void init() {
		toLogin = new TOLogin();
	}
	
	public void authenticate() {
		try {
			loginViewModel.authenticate(toLogin);
			FacesUtils.redirect(IScreensRedirect.EXECUTION_LOGS);
		} catch (Exception e) {
			exceptionHandler(e, getScreenBundle().getString("error_summary"));
		}
	}
	
	public void authenticateAgain() {
		try {
			loginViewModel.authenticate(toLogin);
		} catch (Exception e) {
			exceptionHandler(e, getScreenBundle().getString("error_summary"));
		}
	}
	
	public TOLogin getToLogin() {
		return toLogin;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.login";
	}
}

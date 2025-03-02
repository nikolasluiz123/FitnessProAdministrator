package br.com.administrator.managedbean.login;

import java.io.Serializable;

import br.com.administrator.to.TOLogin;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.LoginViewModel;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("loginMBean")
@ViewScoped
public class LoginMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TOLogin toLogin;
	
	@PostConstruct
	public void init() {
		toLogin = new TOLogin();
	}
	
	@Inject
	private LoginViewModel loginViewModel;
	
	public void authenticate() {
		try {
			loginViewModel.authenticate(toLogin);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        externalContext.redirect(externalContext.getRequestContextPath() + "/htdocs/logs/logs_service_execution.jsf");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage(), "Erro de Autenticação");
			e.printStackTrace();
		}
	}
	
	public void register() {
		
	}
	
	public TOLogin getToLogin() {
		return toLogin;
	}
}

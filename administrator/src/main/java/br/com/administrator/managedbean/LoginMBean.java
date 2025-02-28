package br.com.administrator.managedbean;

import java.io.Serializable;

import br.com.administrator.to.TOLogin;
import br.com.administrator.viewmodel.LoginViewModel;
import jakarta.annotation.PostConstruct;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void register() {
		
	}
	
	public TOLogin getToLogin() {
		return toLogin;
	}
}

package br.com.administrator.viewmodel.login;

import java.io.Serializable;

import br.com.administrator.service.webclient.AuthenticationWebClient;
import br.com.administrator.to.TOLogin;
import br.com.administrator.utils.TokenUtil;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class LoginViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final AuthenticationWebClient authenticationWebClient;

	@Inject
	public LoginViewModel(AuthenticationWebClient authenticationWebClient) {
        this.authenticationWebClient = authenticationWebClient;
	}

	public void authenticate(TOLogin toLogin) throws Exception {
		String token = authenticationWebClient.authenticate(toLogin.getEmail(), toLogin.getPassword());
		TokenUtil.saveToken(token);
	}
}

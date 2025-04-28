package br.com.administrator.viewmodel.login;

import java.io.Serializable;

import br.com.administrator.service.webclient.AuthenticationWebClient;
import br.com.administrator.to.TOLogin;
import br.com.administrator.utils.AuthSessionUtils;
import br.com.fitnesspro.shared.communication.helper.HashHelper;
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
		String hashedPassword = HashHelper.INSTANCE.applyHash(toLogin.getPassword());
		String token = authenticationWebClient.authenticate(toLogin.getEmail(), hashedPassword);
		AuthSessionUtils.saveUserInfos(token, toLogin.getEmail(), hashedPassword);
	}
}

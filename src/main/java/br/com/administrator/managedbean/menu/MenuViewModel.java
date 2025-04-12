package br.com.administrator.managedbean.menu;

import java.io.Serializable;

import br.com.administrator.service.webclient.AuthenticationWebClient;
import br.com.administrator.utils.AuthSessionUtils;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class MenuViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final AuthenticationWebClient webClient;

	@Inject
	public MenuViewModel(AuthenticationWebClient webClient) {
        this.webClient = webClient;
	}
	
	public void logout() throws Exception {
		String userEmail = AuthSessionUtils.getUserEmail();
		String userPassword = AuthSessionUtils.getUserPassword();
		
		webClient.logout(userEmail, userPassword);
	}
	
}

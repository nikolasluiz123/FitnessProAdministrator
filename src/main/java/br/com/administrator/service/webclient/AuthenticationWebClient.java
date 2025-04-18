package br.com.administrator.service.webclient;

import java.net.ConnectException;

import br.com.administrator.service.IAuthenticationService;
import br.com.administrator.service.exception.ServiceException;
import br.com.fitnesspro.shared.communication.dtos.general.AuthenticationDTO;
import br.com.fitnesspro.shared.communication.responses.AuthenticationServiceResponse;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;

@ApplicationScoped
public class AuthenticationWebClient extends AbstractWebClient {

	private final IAuthenticationService service;
	
	public AuthenticationWebClient() {
		this.service = null;
	}
	
	@Inject
	public AuthenticationWebClient(IAuthenticationService service) {
		this.service = service;
	}
	
	@Nullable
	public String authenticate(@NotNull String email, @NotNull String password) throws Exception {
		try {
			String applicationToken = getApplicationJWTToken();
			
			AuthenticationDTO authenticationDTO = new AuthenticationDTO();
			authenticationDTO.setEmail(email);
			authenticationDTO.setPassword(password);
			authenticationDTO.setAdminAuth(true);
			
			Call<AuthenticationServiceResponse> authenticationCall = service.authenticate(applicationToken, authenticationDTO);
			AuthenticationServiceResponse response = getAuthResponseBody(authenticationCall);
			
			if (!response.getSuccess()) {
				throw new ServiceException(response.getError());
			}
			
			return response.getToken();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public void logout(@NotNull String email, @NotNull String password) throws Exception {
		try {
			String token = getFormatedToken();
			
			AuthenticationDTO authenticationDTO = new AuthenticationDTO();
			authenticationDTO.setEmail(email);
			authenticationDTO.setPassword(password);
			
			Call<AuthenticationServiceResponse> authenticationCall = service.logout(token, authenticationDTO);
			AuthenticationServiceResponse response = getAuthResponseBody(authenticationCall);
			
			if (!response.getSuccess()) {
				throw new ServiceException(response.getError());
			}
			
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
}

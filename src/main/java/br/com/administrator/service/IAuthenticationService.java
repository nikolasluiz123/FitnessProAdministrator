package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.general.AuthenticationDTO;
import br.com.fitnesspro.shared.communication.responses.AuthenticationServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IAuthenticationService {

	public static final String AUTHENTICATE_END_POINT = EndPointsV1.AUTHENTICATION + EndPointsV1.AUTHENTICATION_LOGIN;

	public static final String LOGOUT_END_POINT = EndPointsV1.AUTHENTICATION + EndPointsV1.AUTHENTICATION_LOGOUT;

	@POST(AUTHENTICATE_END_POINT)
	Call<AuthenticationServiceResponse> authenticate(@Header("Authorization") @NotNull String applicationToken,
													 @Body @NotNull AuthenticationDTO authenticationDTO);

	@POST(LOGOUT_END_POINT)
	Call<AuthenticationServiceResponse> logout(@Header("Authorization") @NotNull String token, 
											  @Body @NotNull AuthenticationDTO authenticationDTO);

}

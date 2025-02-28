package br.com.administrator.service.webclient;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.fitnesspro.shared.communication.responses.AuthenticationServiceResponse;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import retrofit2.Call;
import retrofit2.Response;

public abstract class AbstractWebClient {

	protected PersistenceServiceResponse getPersistenceResponseBody(Call<PersistenceServiceResponse> call) throws IOException {
		Type type = new TypeToken<PersistenceServiceResponse>() {}.getType();
		Response<PersistenceServiceResponse> response = call.execute();
		
        return response.body() != null ? response.body() : new Gson().fromJson(response.errorBody().charStream(), type);
	}

	protected AuthenticationServiceResponse getAuthResponseBody(Call<AuthenticationServiceResponse> call) throws IOException {
		Type type = new TypeToken<AuthenticationServiceResponse>() {}.getType();
		Response<AuthenticationServiceResponse> response = call.execute();
		
        return response.body() != null ? response.body() : new Gson().fromJson(response.errorBody().charStream(), type);
	}
	
	protected <DTO> ReadServiceResponse<DTO> getReadResponseBody(Call<ReadServiceResponse<DTO>> call) throws IOException {
		Type type = new TypeToken<ReadServiceResponse<DTO>>() {}.getType();
		Response<ReadServiceResponse<DTO>> response = call.execute();
		
        return response.body() != null ? response.body() : new Gson().fromJson(response.errorBody().charStream(), type);
	}
	
	protected FitnessProServiceResponse getResponseBody(Call<FitnessProServiceResponse> call) throws IOException {
		Type type = new TypeToken<FitnessProServiceResponse>() {}.getType();
		Response<FitnessProServiceResponse> response = call.execute();
		
        return response.body() != null ? response.body() : new Gson().fromJson(response.errorBody().charStream(), type);
	}
}

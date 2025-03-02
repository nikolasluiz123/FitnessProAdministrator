package br.com.administrator.service.webclient;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.administrator.utils.TokenUtil;
import br.com.fitnesspro.shared.communication.responses.AuthenticationServiceResponse;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import retrofit2.Call;
import retrofit2.Response;

public abstract class AbstractWebClient {
	
	protected String getFormatedToken() {
		return "Bearer " + TokenUtil.getToken();
	}

	protected PersistenceServiceResponse getPersistenceResponseBody(Call<PersistenceServiceResponse> call) throws IOException {
		Type type = new TypeToken<PersistenceServiceResponse>(){}.getType();
		Response<PersistenceServiceResponse> response = call.execute();
		
        return response.body() != null ? response.body() : new Gson().fromJson(response.errorBody().charStream(), type);
	}

	protected AuthenticationServiceResponse getAuthResponseBody(Call<AuthenticationServiceResponse> call) throws IOException {
		Type type = new TypeToken<AuthenticationServiceResponse>(){}.getType();
		Response<AuthenticationServiceResponse> response = call.execute();
		
        return response.body() != null ? response.body() : new Gson().fromJson(response.errorBody().charStream(), type);
	}
	
	protected <DTO> ReadServiceResponse<DTO> getReadResponseBody(Call<ReadServiceResponse<DTO>> call, Class<DTO> clazz) throws IOException {
	    Response<ReadServiceResponse<DTO>> response = call.execute();

	    if (response.body() != null) {
	        return response.body();
	    } else {
	        Type type = TypeToken.getParameterized(ReadServiceResponse.class, clazz).getType();
	        return new Gson().fromJson(response.errorBody().charStream(), type);
	    }
	}
	
	protected <T> SingleValueServiceResponse<T> getSingleResponseBody(Call<SingleValueServiceResponse<T>> call, Class<T> clazz) throws IOException {
	    Response<SingleValueServiceResponse<T>> response = call.execute();

	    if (response.body() != null) {
	        return response.body();
	    } else {
	        Type type = TypeToken.getParameterized(SingleValueServiceResponse.class, clazz).getType();
	        return new Gson().fromJson(response.errorBody().charStream(), type);
	    }
	}
	
	protected FitnessProServiceResponse getResponseBody(Call<FitnessProServiceResponse> call) throws IOException {
		Type type = new TypeToken<FitnessProServiceResponse>(){}.getType();
		Response<FitnessProServiceResponse> response = call.execute();
		
        return response.body() != null ? response.body() : new Gson().fromJson(response.errorBody().charStream(), type);
	}
}

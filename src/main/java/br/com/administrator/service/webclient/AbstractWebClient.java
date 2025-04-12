package br.com.administrator.service.webclient;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.administrator.service.exception.ServiceException;
import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.administrator.utils.TokenUtil;
import br.com.fitnesspro.shared.communication.responses.AuthenticationServiceResponse;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.servlet.http.HttpServletResponse;
import retrofit2.Call;
import retrofit2.Response;

public abstract class AbstractWebClient {
	
	protected String getFormatedToken() {
		return "Bearer " + TokenUtil.getToken();
	}
	
	protected String getApplicationJWTToken() {
		return "Bearer " + System.getProperty("JWT_TOKEN");
	}

	protected PersistenceServiceResponse getPersistenceResponseBody(Call<PersistenceServiceResponse> call) throws Exception {
//		Type type = new TypeToken<PersistenceServiceResponse>(){}.getType();
		Response<PersistenceServiceResponse> response = call.execute();
		
		if (response.body() != null) {
			return response.body();
		} else if (response.errorBody() != null) {
			Reader reader = response.errorBody().charStream();
			
			if (response.code() == HttpServletResponse.SC_UNAUTHORIZED) {
				AuthenticationServiceResponse authResponse = GsonUtils.getDefaultGson().fromJson(reader, AuthenticationServiceResponse.class);
				
				return new PersistenceServiceResponse(authResponse.getCode(), 
													  authResponse.getSuccess(), 
													  authResponse.getError(), 
													  authResponse.getErrorType(), 
													  null);
			} else {
				throw new ServiceException("A resposta de erro não foi tratada");
			}
			
		} else {
			throw new ServiceException("Não foi encontrada uma resposta");
		}
	}

	protected AuthenticationServiceResponse getAuthResponseBody(Call<AuthenticationServiceResponse> call) throws Exception {
//		Type type = new TypeToken<AuthenticationServiceResponse>(){}.getType();
		Response<AuthenticationServiceResponse> response = call.execute();
		
		if (response.body() != null) {
			return response.body();
		} else if (response.errorBody() != null) {
			Reader reader = response.errorBody().charStream();
			
			if (response.code() == HttpServletResponse.SC_UNAUTHORIZED) {
				return GsonUtils.getDefaultGson().fromJson(reader, AuthenticationServiceResponse.class);
			} else {
				throw new ServiceException("A resposta de erro não foi tratada");
			}
			
		} else {
			throw new ServiceException("Não foi encontrada uma resposta");
		}
	}
	
	protected <DTO> ReadServiceResponse<DTO> getReadResponseBody(Call<ReadServiceResponse<DTO>> call, Class<DTO> clazz) throws Exception {
	    Response<ReadServiceResponse<DTO>> response = call.execute();

		if (response.body() != null) {
			return response.body();
		} else if (response.errorBody() != null) {
			Reader reader = response.errorBody().charStream();
			
			if (response.code() == HttpServletResponse.SC_UNAUTHORIZED) {
				AuthenticationServiceResponse authResponse = GsonUtils.getDefaultGson().fromJson(reader, AuthenticationServiceResponse.class);
				
				return new ReadServiceResponse<DTO>(new ArrayList<>(),
											   		authResponse.getCode(), 
											   		authResponse.getSuccess(), 
											   		authResponse.getError(), 
											   		authResponse.getErrorType());
			} else {
		        Type type = TypeToken.getParameterized(ReadServiceResponse.class, clazz).getType();
		        return new Gson().fromJson(response.errorBody().charStream(), type);
			}
			
		} else {
			throw new ServiceException("Não foi encontrada uma resposta");
		}
	}
	
	protected <T> SingleValueServiceResponse<T> getSingleResponseBody(Call<SingleValueServiceResponse<T>> call, Class<T> clazz) throws Exception {
	    Response<SingleValueServiceResponse<T>> response = call.execute();

		if (response.body() != null) {
			return response.body();
		} else if (response.errorBody() != null) {
			Reader reader = response.errorBody().charStream();
			
			if (response.code() == HttpServletResponse.SC_UNAUTHORIZED) {
				AuthenticationServiceResponse authResponse = GsonUtils.getDefaultGson().fromJson(reader, AuthenticationServiceResponse.class);
				
				return new SingleValueServiceResponse<T>(null,
												   		 authResponse.getCode(), 
												   		 authResponse.getSuccess(), 
												   		 authResponse.getError(), 
												   		 authResponse.getErrorType());
			} else {
		        Type type = TypeToken.getParameterized(SingleValueServiceResponse.class, clazz).getType();
		        return new Gson().fromJson(response.errorBody().charStream(), type);
			}
			
		} else {
			throw new ServiceException("Não foi encontrada uma resposta");
		}
	}
	
	protected FitnessProServiceResponse getResponseBody(Call<FitnessProServiceResponse> call) throws Exception {
//		Type type = new TypeToken<FitnessProServiceResponse>(){}.getType();
		Response<FitnessProServiceResponse> response = call.execute();
		
		if (response.body() != null) {
			return response.body();
		} else if (response.errorBody() != null) {
			Reader reader = response.errorBody().charStream();
			
			if (response.code() == HttpServletResponse.SC_UNAUTHORIZED) {
				AuthenticationServiceResponse authResponse = GsonUtils.getDefaultGson().fromJson(reader, AuthenticationServiceResponse.class);
				
				return new FitnessProServiceResponse(authResponse.getCode(), 
											   		 authResponse.getSuccess(), 
											   		 authResponse.getError(), 
											   		 authResponse.getErrorType());
			} else {
				throw new ServiceException("A resposta de erro não foi tratada");
			}
			
		} else {
			throw new ServiceException("Não foi encontrada uma resposta");
		}
	}
}

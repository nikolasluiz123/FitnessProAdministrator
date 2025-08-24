package br.com.administrator.service.webclient;

import java.io.Reader;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.service.exception.ServiceException;
import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.administrator.utils.AuthSessionUtils;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.dtos.common.BaseDTO;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumErrorType;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import br.com.fitnesspro.shared.communication.responses.AuthenticationServiceResponse;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import br.com.fitnesspro.shared.communication.responses.interfaces.IFitnessProServiceResponse;
import jakarta.servlet.http.HttpServletResponse;
import retrofit2.Call;
import retrofit2.Response;

public abstract class AbstractWebClient {
	
	protected String getFormatedToken() {
		return "Bearer " + AuthSessionUtils.getToken();
	}
	
	protected String getApplicationJWTToken() {
		return "Bearer " + System.getProperty("JWT_TOKEN");
	}
	
	protected void validateResponse(IFitnessProServiceResponse response) throws Exception {
		if (!response.getSuccess()) {
			if (response.getErrorType() == EnumErrorType.EXPIRED_TOKEN) {
				throw new ExpiredTokenException(getServiceBundleString("expired_token"));
			} else if (response.getErrorType() == EnumErrorType.INVALID_TOKEN) {
				throw new NotFoundTokenException(getServiceBundleString("not_found_token"));
			} else {
				throw new ServiceException(response.getError());
			}
		}
	}

	protected <DTO extends BaseDTO> PersistenceServiceResponse<DTO> getPersistenceResponseBody(Call<PersistenceServiceResponse<DTO>> call, Class<DTO> clazz) throws Exception {
		Response<PersistenceServiceResponse<DTO>> response = call.execute();
		
		if (response.body() != null) {
			return response.body();
		} else if (response.errorBody() != null) {
			Reader reader = response.errorBody().charStream();
			
			if (response.code() == HttpServletResponse.SC_UNAUTHORIZED) {
				AuthenticationServiceResponse authResponse = GsonUtils.getDefaultGson().fromJson(reader, AuthenticationServiceResponse.class);
				
				return new PersistenceServiceResponse<DTO>(authResponse.getCode(), 
													  authResponse.getSuccess(), 
													  authResponse.getError(), 
													  authResponse.getErrorType(), 
													  null);
			} else {
				Type type = TypeToken.getParameterized(PersistenceServiceResponse.class, clazz).getType();
		        return GsonUtils.getDefaultGson().fromJson(response.errorBody().charStream(), type);
			}
			
		} else {
			throw new ServiceException(getServiceBundleString("fail_get_response_message"));
		}
	}

	protected AuthenticationServiceResponse getAuthResponseBody(Call<AuthenticationServiceResponse> call) throws Exception {
		Response<AuthenticationServiceResponse> response = call.execute();
		
		if (response.body() != null) {
			return response.body();
		} else if (response.errorBody() != null) {
			Reader reader = response.errorBody().charStream();
			return GsonUtils.getDefaultGson().fromJson(reader, AuthenticationServiceResponse.class);
		} else {
			throw new ServiceException(getServiceBundleString("fail_get_response_message"));
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
		        return GsonUtils.getDefaultGson().fromJson(response.errorBody().charStream(), type);
			}
			
		} else {
			throw new ServiceException(getServiceBundleString("fail_get_response_message"));
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
		        return GsonUtils.getDefaultGson().fromJson(response.errorBody().charStream(), type);
			}
			
		} else {
			throw new ServiceException(getServiceBundleString("fail_get_response_message"));
		}
	}
	
	protected FitnessProServiceResponse getResponseBody(Call<FitnessProServiceResponse> call) throws Exception {
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
				return GsonUtils.getDefaultGson().fromJson(reader, FitnessProServiceResponse.class);
			}
			
		} else {
			throw new ServiceException(getServiceBundleString("fail_get_response_message"));
		}
	}
	
	protected ResourceBundle getServiceBundle() {
		return FacesUtils.getResourceBundle(ICommonBundlePaths.SERVICE);
	}
	
	protected String getServiceBundleString(String key, Object...replaces) {
		String text = getServiceBundleString(key);
		return MessageFormat.format(text, replaces);
	}
	
	protected String getServiceBundleString(String key) {
		return getServiceBundle().getString(key);
	}
}

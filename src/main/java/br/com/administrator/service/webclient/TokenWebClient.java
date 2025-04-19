package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import com.google.gson.Gson;

import br.com.administrator.service.ITokenService;
import br.com.administrator.service.exception.ServiceException;
import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenGenerationDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.ServiceTokenFilter;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class TokenWebClient extends AbstractWebClient {
	
	private final ITokenService service;
	
	public TokenWebClient() {
		this.service = null;
	}
	
	@Inject
	public TokenWebClient(ITokenService service) {
		this.service = service;
	}
	
	public SingleValueServiceResponse<ServiceTokenDTO> generateToken(ServiceTokenGenerationDTO dto) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<SingleValueServiceResponse<ServiceTokenDTO>> serviceCall = service.generateToken(token, dto);
			SingleValueServiceResponse<ServiceTokenDTO> response = getSingleResponseBody(serviceCall, ServiceTokenDTO.class);
			validateResponse(response);
			
			return response;
			
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public void invalidateToken(String tokenId) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<FitnessProServiceResponse> serviceCall = service.invalidateToken(token, tokenId);
			FitnessProServiceResponse response = getResponseBody(serviceCall);
			validateResponse(response);
			
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public void invalidateAllTokens() throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<FitnessProServiceResponse> serviceCall = service.invalidateAllTokens(token);
			FitnessProServiceResponse response = getResponseBody(serviceCall);
			validateResponse(response);
			
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public String getSecretKey() throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<SingleValueServiceResponse<String>> serviceCall = service.getSecretKey(token);
			SingleValueServiceResponse<String> response = getSingleResponseBody(serviceCall, String.class);
			validateResponse(response);
			
			return response.getValue();
			
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public List<ServiceTokenDTO> getListServiceToken(ServiceTokenFilter filter, CommonPageInfos pageInfos) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<ReadServiceResponse<ServiceTokenDTO>> serviceCall = service.getListServiceToken(token, defaultGson.toJson(filter), defaultGson.toJson(pageInfos));
			ReadServiceResponse<ServiceTokenDTO> response = getReadResponseBody(serviceCall, ServiceTokenDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public Integer getCountListServiceToken(ServiceTokenFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountServiceToken(token, defaultGson.toJson(filter));
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			validateResponse(response);
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public ServiceTokenDTO getTokenService(String tokenId) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<SingleValueServiceResponse<ServiceTokenDTO>> serviceCall = service.getTokenService(token, tokenId);
			SingleValueServiceResponse<ServiceTokenDTO> response = getSingleResponseBody(serviceCall, ServiceTokenDTO.class);
			validateResponse(response);
			
			return response.getValue();
			
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
}

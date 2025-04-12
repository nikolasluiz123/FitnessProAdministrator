package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import br.com.administrator.service.ICacheService;
import br.com.administrator.service.exception.ServiceException;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheClearConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheEntryDTO;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class CacheWebClient extends AbstractWebClient {
	
	private final ICacheService service;
	
	public CacheWebClient() {
		this.service = null;
	}
	
	@Inject
	public CacheWebClient(ICacheService service) {
		this.service = service;
	}
	
	public List<CacheDTO> getListCache() throws Exception {
		try {
			String token = getFormatedToken();

			Call<ReadServiceResponse<CacheDTO>> serviceCall = service.getListCache(token);
			ReadServiceResponse<CacheDTO> response = getReadResponseBody(serviceCall, CacheDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public List<CacheEntryDTO> getListCacheEntries(String cacheName) throws Exception {
		try {
			String token = getFormatedToken();

			Call<ReadServiceResponse<CacheEntryDTO>> serviceCall = service.getListCacheEntries(token, cacheName);
			ReadServiceResponse<CacheEntryDTO> response = getReadResponseBody(serviceCall, CacheEntryDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public void clearCache(CacheClearConfigDTO config) throws Exception {
		try {
			String token = getFormatedToken();

			Call<FitnessProServiceResponse> serviceCall = service.clearCache(token, config);
			FitnessProServiceResponse response = getResponseBody(serviceCall);
			validateResponse(response);
			
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
}

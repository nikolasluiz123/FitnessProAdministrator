package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import com.google.gson.Gson;

import br.com.administrator.service.IAcademyService;
import br.com.administrator.service.exception.ServiceException;
import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.AcademyFilter;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class AcademyWebClient extends AbstractWebClient {
	
	private final IAcademyService service;
	
	public AcademyWebClient() {
		this.service = null;
	}
	
	@Inject
	public AcademyWebClient(IAcademyService service) {
		this.service = service;
	}
	
	public List<AcademyDTO> getListAcademy(AcademyFilter filter, CommonPageInfos pageInfos) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<ReadServiceResponse<AcademyDTO>> serviceCall = service.getListAcademy(token, 
																					   defaultGson.toJson(filter), 
																					   defaultGson.toJson(pageInfos));
			
			ReadServiceResponse<AcademyDTO> response = getReadResponseBody(serviceCall, AcademyDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public Integer getCountListAcademy(AcademyFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountAcademy(token, defaultGson.toJson(filter));
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			validateResponse(response);
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public PersistenceServiceResponse<AcademyDTO> saveAcademy(AcademyDTO dto) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<PersistenceServiceResponse<AcademyDTO>> serviceCall = service.saveAcademy(token, dto);
			PersistenceServiceResponse<AcademyDTO> response = getPersistenceResponseBody(serviceCall, AcademyDTO.class);
			validateResponse(response);
			
			return response;
			
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}

}

package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import com.google.gson.Gson;

import br.com.administrator.service.IPersonService;
import br.com.administrator.service.exception.ServiceException;
import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.PersonFilter;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class PersonWebClient extends AbstractWebClient {

	private final IPersonService service;

	public PersonWebClient() {
		this.service = null;
	}

	@Inject
	public PersonWebClient(IPersonService service) {
		this.service = service;
	}

	public List<PersonDTO> getListPerson(PersonFilter filter, CommonPageInfos pageInfos) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<ReadServiceResponse<PersonDTO>> serviceCall = service.getListPerson(token, 
																				   	 defaultGson.toJson(filter), 
																				     defaultGson.toJson(pageInfos));
			
			ReadServiceResponse<PersonDTO> response = getReadResponseBody(serviceCall, PersonDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public Integer getCountListPerson(PersonFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountPerson(token, defaultGson.toJson(filter));
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			validateResponse(response);
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public PersistenceServiceResponse<PersonDTO> savePerson(PersonDTO dto) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<PersistenceServiceResponse<PersonDTO>> serviceCall = service.savePerson(token, dto);
			PersistenceServiceResponse<PersonDTO> response = getPersistenceResponseBody(serviceCall, PersonDTO.class);
			validateResponse(response);
			
			return response;
			
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
}

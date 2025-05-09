package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import br.com.administrator.service.IApplicationService;
import br.com.administrator.service.exception.ServiceException;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class ApplicationWebClient extends AbstractWebClient {
	
	private final IApplicationService service;
	
	public ApplicationWebClient() {
		this.service = null;
	}
	
	@Inject
	public ApplicationWebClient(IApplicationService service) {
		this.service = service;
	}
	
	public PersistenceServiceResponse<ApplicationDTO> saveApplication(ApplicationDTO dto) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<PersistenceServiceResponse<ApplicationDTO>> serviceCall = service.saveApplication(token, dto);
			PersistenceServiceResponse<ApplicationDTO> response = getPersistenceResponseBody(serviceCall, ApplicationDTO.class);
			validateResponse(response);
			
			return response;
			
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public List<ApplicationDTO> getListApplication() throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<ReadServiceResponse<ApplicationDTO>> serviceCall = service.getListApplication(token);
			ReadServiceResponse<ApplicationDTO> response = getReadResponseBody(serviceCall, ApplicationDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
}

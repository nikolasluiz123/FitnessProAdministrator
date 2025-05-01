package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import br.com.administrator.service.IScheduledTaskService;
import br.com.administrator.service.exception.ServiceException;
import br.com.fitnesspro.shared.communication.dtos.scheduledtask.ScheduledTaskDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class ScheduledTaskWebClient extends AbstractWebClient {
	
	private final IScheduledTaskService service;
	
	public ScheduledTaskWebClient() {
		this.service = null;
	}
	
	@Inject
	public ScheduledTaskWebClient(IScheduledTaskService service) {
		this.service = service;
	}
	
	public List<ScheduledTaskDTO> getListScheduledTask() throws Exception {
		try {
			String token = getFormatedToken();
			Call<ReadServiceResponse<ScheduledTaskDTO>> serviceCall = service.getListScheduledTask(token);
			
			ReadServiceResponse<ScheduledTaskDTO> response = getReadResponseBody(serviceCall, ScheduledTaskDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public PersistenceServiceResponse<ScheduledTaskDTO> saveScheduledTask(ScheduledTaskDTO dto) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<PersistenceServiceResponse<ScheduledTaskDTO>> serviceCall = service.saveScheduledTask(token, dto);
			PersistenceServiceResponse<ScheduledTaskDTO> response = getPersistenceResponseBody(serviceCall, ScheduledTaskDTO.class);
			validateResponse(response);
			
			return response;
			
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}

}

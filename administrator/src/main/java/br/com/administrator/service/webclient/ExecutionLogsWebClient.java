package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import br.com.administrator.service.IExecutionLogsService;
import br.com.administrator.service.exception.ServiceException;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.filter.ExecutionLogsFilter;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class ExecutionLogsWebClient extends AbstractWebClient {

	private final IExecutionLogsService service;
	
	public ExecutionLogsWebClient() {
		this.service = null;
	}
	
	@Inject
	public ExecutionLogsWebClient(IExecutionLogsService service) {
		this.service = service;
	}
	
	public List<ExecutionLogDTO> getListExecutionLog(ExecutionLogsFilter filter, CommonPageInfos pageInfos) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<ReadServiceResponse<ExecutionLogDTO>> serviceCall = service.getListExecutionLog(token, filter, pageInfos);
			ReadServiceResponse<ExecutionLogDTO> response = getReadResponseBody(serviceCall, ExecutionLogDTO.class);
			
			if (!response.getSuccess()) {
				throw new ServiceException(response.getError());
			}
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public Integer getCountListExecutionLog(ExecutionLogsFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountListExecutionLog(token, filter);
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			
			if (!response.getSuccess()) {
				throw new ServiceException(response.getError());
			}
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
}

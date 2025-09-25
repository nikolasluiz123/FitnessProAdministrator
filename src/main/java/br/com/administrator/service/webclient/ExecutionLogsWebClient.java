package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import com.google.gson.Gson;

import br.com.administrator.service.IExecutionLogsService;
import br.com.administrator.service.exception.ServiceException;
import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogPackageDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogSubPackageDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogSubPackageFilter;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogsFilter;
import br.com.fitnesspro.shared.communication.query.filter.ExecutionLogsPackageFilter;
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
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<ReadServiceResponse<ExecutionLogDTO>> serviceCall = service.getListExecutionLog(token, 
																								 defaultGson.toJson(filter), 
																								 defaultGson.toJson(pageInfos));
			
			ReadServiceResponse<ExecutionLogDTO> response = getReadResponseBody(serviceCall, ExecutionLogDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public Integer getCountListExecutionLog(ExecutionLogsFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountListExecutionLog(token, defaultGson.toJson(filter));
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			validateResponse(response);
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public List<ExecutionLogPackageDTO> getListExecutionLogPackage(ExecutionLogsPackageFilter filter, CommonPageInfos pageInfos) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<ReadServiceResponse<ExecutionLogPackageDTO>> serviceCall = service.getListExecutionLogPackage(token, 
																											   defaultGson.toJson(filter), 
																											   defaultGson.toJson(pageInfos));
			
			ReadServiceResponse<ExecutionLogPackageDTO> response = getReadResponseBody(serviceCall, ExecutionLogPackageDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public Integer getCountListExecutionLogPackage(ExecutionLogsPackageFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountListExecutionLogPackage(token, defaultGson.toJson(filter));
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			validateResponse(response);
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public List<ExecutionLogSubPackageDTO> getListExecutionLogSubPackage(ExecutionLogSubPackageFilter filter, CommonPageInfos pageInfos) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<ReadServiceResponse<ExecutionLogSubPackageDTO>> serviceCall = service.getListExecutionLogSubPackage(token,
																													defaultGson.toJson(filter), 
																													defaultGson.toJson(pageInfos));
			
			ReadServiceResponse<ExecutionLogSubPackageDTO> response = getReadResponseBody(serviceCall, ExecutionLogSubPackageDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}

	public Integer getCountListExecutionLogSubPackage(ExecutionLogSubPackageFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountListExecutionLogSubPackage(token, defaultGson.toJson(filter));
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			validateResponse(response);
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
}
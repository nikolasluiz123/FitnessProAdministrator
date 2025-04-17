package br.com.administrator.service.webclient;

import java.net.ConnectException;
import java.util.List;

import com.google.gson.Gson;

import br.com.administrator.service.IDeviceService;
import br.com.administrator.service.exception.ServiceException;
import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.filter.DeviceFilter;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class DeviceWebClient extends AbstractWebClient {
	
	private final IDeviceService service;
	
	public DeviceWebClient() {
		this.service = null;
	}
	
	@Inject
	public DeviceWebClient(IDeviceService service) {
		this.service = service;
	}
	
	
	public List<DeviceDTO> getListDevice(DeviceFilter filter, CommonPageInfos pageInfos) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<ReadServiceResponse<DeviceDTO>> serviceCall = service.getListDevice(token, defaultGson.toJson(filter), defaultGson.toJson(pageInfos));
			ReadServiceResponse<DeviceDTO> response = getReadResponseBody(serviceCall, DeviceDTO.class);
			validateResponse(response);
			
			return response.getValues();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
	public Integer getCountListDevice(DeviceFilter filter) throws Exception {
		try {
			String token = getFormatedToken();
			Gson defaultGson = GsonUtils.getDefaultGson();
			
			Call<SingleValueServiceResponse<Integer>> serviceCall = service.getCountDevice(token, defaultGson.toJson(filter));
			SingleValueServiceResponse<Integer> response = getSingleResponseBody(serviceCall, Integer.class);
			validateResponse(response);
			
			return response.getValue();
		} catch (ConnectException exception) {
			throw new ServiceException("Não foi possível se conectar ao servidor. Tente novamente mais tarde.", exception);
		}
	}
	
}

package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IDeviceService {

	public static final String DEVICE_COUNT_END_POINT = EndPointsV1.DEVICE + EndPointsV1.DEVICE_COUNT;
	
	@GET(EndPointsV1.DEVICE)
	Call<ReadServiceResponse<DeviceDTO>> getListDevice(@Header("Authorization") String token,
												       @Query("filter") @NotNull String filter, 
												       @Query("pageInfos") @NotNull String pageInfos);
	
	@GET(DEVICE_COUNT_END_POINT)
	Call<SingleValueServiceResponse<Integer>> getCountDevice(@Header("Authorization") String token, 
														     @Query("filter") @NotNull String filter);
	
}

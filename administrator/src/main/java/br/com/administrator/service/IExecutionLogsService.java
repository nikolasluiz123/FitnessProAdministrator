package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IExecutionLogsService {

	@GET(EndPointsV1.LOGS)
	Call<ReadServiceResponse<ExecutionLogDTO>> getListExecutionLog(@Header("Authorization") String token,
															       @Query("filter") @NotNull String filter, 
															       @Query("pageInfos") @NotNull String pageInfos);
	
	@GET(EndPointsV1.LOGS + EndPointsV1.LOGS_COUNT)
	Call<SingleValueServiceResponse<Integer>> getCountListExecutionLog(@Header("Authorization") String token, 
			  														   @Query("filter") @NotNull String filter);
	
}

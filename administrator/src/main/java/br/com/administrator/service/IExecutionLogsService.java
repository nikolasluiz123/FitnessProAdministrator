package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.filter.ExecutionLogsFilter;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IExecutionLogsService {

	@POST(EndPointsV1.LOGS)
	Call<ReadServiceResponse<ExecutionLogDTO>> getListExecutionLog(@Header("Authorization") String token,
																   @Body @NotNull ExecutionLogsFilter fiter, 
																   @Query("pageInfos") @NotNull CommonPageInfos pageInfos);
	
	@POST(EndPointsV1.LOGS + EndPointsV1.LOGS_COUNT)
	Call<SingleValueServiceResponse<Integer>> getCountListExecutionLog(@Header("Authorization") String token,
													   				   @Body @NotNull ExecutionLogsFilter fiter);
	
}

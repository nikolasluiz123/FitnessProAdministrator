package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogPackageDTO;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IExecutionLogsService {

	public static final String END_POINT_LOGS_COUNT = EndPointsV1.LOGS + EndPointsV1.LOGS_COUNT;
	public static final String END_POINT_LOGS_PACKAGE_COUNT = EndPointsV1.LOGS + EndPointsV1.LOGS_PACKAGE_COUNT;
	public static final String END_POINT_LOGS_PACKAGE_LIST = EndPointsV1.LOGS + EndPointsV1.LOGS_PACKAGE;

	@GET(EndPointsV1.LOGS)
	Call<ReadServiceResponse<ExecutionLogDTO>> getListExecutionLog(@Header("Authorization") String token,
															       @Query("filter") @NotNull String filter, 
															       @Query("pageInfos") @NotNull String pageInfos);
	
	@GET(END_POINT_LOGS_COUNT)
	Call<SingleValueServiceResponse<Integer>> getCountListExecutionLog(@Header("Authorization") String token, 
			  														   @Query("filter") @NotNull String filter);
	
	@GET(END_POINT_LOGS_PACKAGE_LIST)
	Call<ReadServiceResponse<ExecutionLogPackageDTO>> getListExecutionLogPackage(@Header("Authorization") String token,
																		         @Query("filter") @NotNull String filter, 
																		         @Query("pageInfos") @NotNull String pageInfos);
	
	@GET(END_POINT_LOGS_PACKAGE_COUNT)
	Call<SingleValueServiceResponse<Integer>> getCountListExecutionLogPackage(@Header("Authorization") String token, 
			  														   		  @Query("filter") @NotNull String filter);
	
}

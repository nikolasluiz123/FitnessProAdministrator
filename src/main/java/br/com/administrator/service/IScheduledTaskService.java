package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.scheduledtask.ScheduledTaskDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import jakarta.annotation.Nonnull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IScheduledTaskService {

	@GET(EndPointsV1.SCHEDULED_TASK)
	Call<ReadServiceResponse<ScheduledTaskDTO>> getListScheduledTask(@Header("Authorization") String token);
	
	@POST(EndPointsV1.SCHEDULED_TASK)
	Call<PersistenceServiceResponse<ScheduledTaskDTO>> saveScheduledTask(@Header("Authorization") String token, @Body @Nonnull ScheduledTaskDTO scheduledTaskDTO);
	
	
}

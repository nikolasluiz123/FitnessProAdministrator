package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IApplicationService {

	@GET(EndPointsV1.APPLICATION)
	Call<ReadServiceResponse<ApplicationDTO>> getListApplication(@Header("Authorization") String token);
	
	@POST(EndPointsV1.APPLICATION)
	Call<PersistenceServiceResponse<ApplicationDTO>> saveApplication(@Header("Authorization") String token, @Body @NotNull ApplicationDTO applicationDTO);
}

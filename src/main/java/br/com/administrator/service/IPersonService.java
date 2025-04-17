package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IPersonService {

	public static final String PERSON_LIST_END_POINT = EndPointsV1.PERSON + EndPointsV1.PERSON_LIST;
	public static final String PERSON_COUNT_END_POINT = EndPointsV1.PERSON + EndPointsV1.PERSON_COUNT;
	
	@GET(PERSON_LIST_END_POINT)
	Call<ReadServiceResponse<PersonDTO>> getListPerson(@Header("Authorization") String token,
												       @Query("filter") @NotNull String filter, 
												       @Query("pageInfos") @NotNull String pageInfos);
	
	@GET(PERSON_COUNT_END_POINT)
	Call<SingleValueServiceResponse<Integer>> getCountPerson(@Header("Authorization") String token, 
														     @Query("filter") @NotNull String filter);
	
}

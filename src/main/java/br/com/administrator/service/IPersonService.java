package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.annotation.Nonnull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IPersonService {

	public static final String PERSON_LIST_END_POINT = EndPointsV1.PERSON + EndPointsV1.PERSON_LIST;
	public static final String PERSON_COUNT_END_POINT = EndPointsV1.PERSON + EndPointsV1.PERSON_COUNT;
	
	@GET(PERSON_LIST_END_POINT)
	Call<ReadServiceResponse<PersonDTO>> getListPerson(@Header("Authorization") String token,
												       @Query("filter") @Nonnull String filter, 
												       @Query("pageInfos") @Nonnull String pageInfos);
	
	@GET(PERSON_COUNT_END_POINT)
	Call<SingleValueServiceResponse<Integer>> getCountPerson(@Header("Authorization") String token, 
														     @Query("filter") @Nonnull String filter);
	
	
	@POST(EndPointsV1.PERSON)
	Call<PersistenceServiceResponse<PersonDTO>> savePerson(@Header("Authorization") String token, @Body @Nonnull PersonDTO personDTO);
	
}

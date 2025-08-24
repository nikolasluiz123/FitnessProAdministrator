package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;
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

public interface IAcademyService {

	public static final String ACADEMY_LIST_END_POINT = EndPointsV1.ACADEMY + EndPointsV1.ACADEMY_LIST;
	public static final String ACADEMY_COUNT_END_POINT = EndPointsV1.ACADEMY + EndPointsV1.ACADEMY_COUNT;
	
	@GET(ACADEMY_LIST_END_POINT)
	Call<ReadServiceResponse<AcademyDTO>> getListAcademy(@Header("Authorization") String token,
													     @Query("filter") @Nonnull String filter, 
													     @Query("pageInfos") @Nonnull String pageInfos);
	
	@GET(ACADEMY_COUNT_END_POINT)
	Call<SingleValueServiceResponse<Integer>> getCountAcademy(@Header("Authorization") String token, 
															  @Query("filter") @Nonnull String filter);
	
	@POST(EndPointsV1.ACADEMY)
	Call<PersistenceServiceResponse<AcademyDTO>> saveAcademy(@Header("Authorization") String token, @Body @Nonnull AcademyDTO academyDTO);
	
	
}

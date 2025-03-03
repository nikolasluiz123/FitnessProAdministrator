package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;
import br.com.fitnesspro.shared.communication.filter.AcademyFilter;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAcademyService {

	public static final String ACADEMY_LIST_END_POINT = EndPointsV1.ACADEMY + EndPointsV1.ACADEMY_LIST;
	public static final String ACADEMY_COUNT_END_POINT = EndPointsV1.ACADEMY + EndPointsV1.ACADEMY_COUNT;
	
	@POST(ACADEMY_LIST_END_POINT)
	Call<ReadServiceResponse<AcademyDTO>> getListAcademy(@Header("Authorization") String token,
													     @Body @NotNull AcademyFilter fiter, 
													     @Query("pageInfos") @NotNull CommonPageInfos pageInfos);
	
	@POST(ACADEMY_COUNT_END_POINT)
	Call<SingleValueServiceResponse<Integer>> getCountAcademy(@Header("Authorization") String token, @Body @NotNull AcademyFilter fiter);
	
	@POST(EndPointsV1.ACADEMY)
	Call<PersistenceServiceResponse> saveAcademy(@Header("Authorization") String token, @Body @NotNull AcademyDTO academyDTO);
	
	
}

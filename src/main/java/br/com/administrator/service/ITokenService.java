package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenGenerationDTO;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import br.com.fitnesspro.shared.communication.responses.SingleValueServiceResponse;
import jakarta.validation.constraints.NotNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ITokenService {

	public static final String TOKEN_COUNT_END_POINT = EndPointsV1.TOKEN + EndPointsV1.TOKENS_COUNT;
	public static final String TOKEN_SECRET_KEY_END_POINT = EndPointsV1.TOKEN + EndPointsV1.TOKEN_SECRET;
	public static final String TOKEN_INVALIDATE_END_POINT = EndPointsV1.TOKEN + EndPointsV1.TOKEN_INVALIDATE;
	public static final String TOKEN_INVALIDATE_ALL_END_POINT = EndPointsV1.TOKEN + EndPointsV1.TOKEN_INVALIDATE_ALL;
	public static final String TOKEN_BY_ID_END_POINT = EndPointsV1.TOKEN + EndPointsV1.TOKEN_BY_ID;

	
	@GET(EndPointsV1.TOKEN)
	Call<ReadServiceResponse<ServiceTokenDTO>> getListServiceToken(@Header("Authorization") String token,
															       @Query("filter") @NotNull String filter, 
															       @Query("pageInfos") @NotNull String pageInfos);
	
	@GET(TOKEN_COUNT_END_POINT)
	Call<SingleValueServiceResponse<Integer>> getCountServiceToken(@Header("Authorization") String token, @Query("filter") @NotNull String filter);
	
	@POST(EndPointsV1.TOKEN)
	Call<SingleValueServiceResponse<ServiceTokenDTO>> generateToken(@Header("Authorization") String token, @Body @NotNull ServiceTokenGenerationDTO serviceTokenGenerationDTO);
	
	@PUT(TOKEN_INVALIDATE_END_POINT)
	Call<FitnessProServiceResponse> invalidateToken(@Header("Authorization") String token, @Path("id") @NotNull String id);
	
	@POST(TOKEN_INVALIDATE_ALL_END_POINT)
	Call<FitnessProServiceResponse> invalidateAllTokens(@Header("Authorization") String token);
	
	@GET(TOKEN_SECRET_KEY_END_POINT)
	Call<SingleValueServiceResponse<String>> getSecretKey(@Header("Authorization") String token);
	
	@GET(TOKEN_BY_ID_END_POINT)
	Call<SingleValueServiceResponse<ServiceTokenDTO>> getTokenService(@Header("Authorization") String token, @Path("id") @NotNull String tokenId);


}

package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheClearConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheEntryDTO;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import br.com.fitnesspro.shared.communication.responses.ReadServiceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICacheService {
	
	public static final String CHACHE_LIST_END_POINT = EndPointsV1.CACHE + EndPointsV1.CACHE_LIST;
	public static final String CHACHE_ENTRIES_LIST_END_POINT = EndPointsV1.CACHE + EndPointsV1.CACHE_ENTRIES + "/{cacheName}";
	public static final String CHACHE_CLEAR_END_POINT = EndPointsV1.CACHE + EndPointsV1.CACHE_CLEAR;

	@GET(CHACHE_LIST_END_POINT)
	Call<ReadServiceResponse<CacheDTO>> getListCache(@Header("Authorization") String token);
	
	@GET(CHACHE_ENTRIES_LIST_END_POINT)
	Call<ReadServiceResponse<CacheEntryDTO>> getListCacheEntries(@Header("Authorization") String token, @Path("cacheName") String cacheName);
	
	@POST(CHACHE_CLEAR_END_POINT)
	Call<FitnessProServiceResponse> clearCache(@Header("Authorization") String token, @Body CacheClearConfigDTO config);
}

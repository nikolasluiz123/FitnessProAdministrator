package br.com.administrator.viewmodel.cache;

import java.io.Serializable;
import java.util.List;

import br.com.administrator.service.webclient.CacheWebClient;
import br.com.administrator.to.TOCache;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheClearConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class CacheSearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final CacheWebClient webClient;

	@Inject
	public CacheSearchViewModel(CacheWebClient webClient) {
		this.webClient = webClient;
	}
	
	public List<TOCache> getListCache() throws Exception {
		List<CacheDTO> caches = webClient.getListCache();
		return caches.stream().map(this::getTOCache).toList();
	}
	
	public void clearAllCaches() throws Exception {
		CacheClearConfigDTO config = new CacheClearConfigDTO();
		config.setClearAll(true);
		
		webClient.clearCache(config);
	}
	
	public void clearCacheWithName(String name) throws Exception {
		CacheClearConfigDTO config = new CacheClearConfigDTO();
		config.setCacheName(name);
		
		webClient.clearCache(config);
	}
	
	private TOCache getTOCache(CacheDTO dto) {
		TOCache to = new TOCache();
		to.setName(dto.getCacheName());
		
		return to;
	}
}

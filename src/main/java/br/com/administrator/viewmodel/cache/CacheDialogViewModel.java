package br.com.administrator.viewmodel.cache;

import java.io.Serializable;
import java.util.List;

import br.com.administrator.mappers.CacheMapper;
import br.com.administrator.service.webclient.CacheWebClient;
import br.com.administrator.to.TOCacheEntry;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheClearConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheEntryDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class CacheDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final CacheWebClient webClient;
	private final CacheMapper mapper;

	@Inject
	public CacheDialogViewModel(CacheWebClient webClient, CacheMapper mapper) {
		this.webClient = webClient;
		this.mapper = mapper;
	}
	
	public List<TOCacheEntry> getListCacheEntries(String cacheName) throws Exception {
		List<CacheEntryDTO> caches = webClient.getListCacheEntries(cacheName);
		return caches.stream().map(c -> mapper.getTOCacheEntryFrom(c)).toList();
	}
	
	public void clearCacheWithKey(String name, String key) throws Exception {
		CacheClearConfigDTO config = new CacheClearConfigDTO();
		config.setCacheName(name);
		config.setCacheKey(key);
		
		webClient.clearCache(config);
	}
}

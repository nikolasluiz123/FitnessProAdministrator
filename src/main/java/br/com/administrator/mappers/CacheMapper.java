package br.com.administrator.mappers;

import br.com.administrator.to.TOCache;
import br.com.administrator.to.TOCacheEntry;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheEntryDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class CacheMapper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public CacheDTO getCacheDTOFrom(TOCache toCache) {
		return modelMapper.map(toCache, CacheDTO.class);
	}
	
	public TOCache getTOCacheFrom(CacheDTO cacheDTO) {
		return modelMapper.map(cacheDTO, TOCache.class);
	}
	
	public CacheEntryDTO getCacheEntryDTOFrom(TOCacheEntry toCacheEntry) {
		return modelMapper.map(toCacheEntry, CacheEntryDTO.class);
	}
	
	public TOCacheEntry getTOCacheEntryFrom(CacheEntryDTO cacheEntryDTO) {
		return modelMapper.map(cacheEntryDTO, TOCacheEntry.class);
	}
}

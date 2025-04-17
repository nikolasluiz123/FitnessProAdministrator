package br.com.administrator.managedbean.cache;

import java.util.ArrayList;
import java.util.List;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOCache;
import br.com.administrator.to.TOCacheEntry;
import br.com.administrator.utils.StringUtils;
import br.com.administrator.viewmodel.cache.CacheDialogViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("cacheDialogMBean")
@ViewScoped
public class CacheDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOCache toCache;
	private List<TOCacheEntry> entriesList;
	
	@Inject
	private CacheDialogViewModel viewModel;
	
	@Override
	protected String getBundleFileName() {
		return "cache_dialog";
	}

	@PostConstruct
	public void init() {
		this.toCache = new TOCache();
		this.entriesList = new ArrayList<>();
		
		loadCacheEntriesList();
	}
	
	private void loadCacheEntriesList() {
		if (StringUtils.isNotNull(this.toCache.getName())) {
			try {
				this.entriesList = viewModel.getListCacheEntries(this.toCache.getName());
			} catch(ExpiredTokenException | NotFoundTokenException exception) {
				exceptionHandler(exception, getBundleString("load_cache_entries_list_error_summary"));
				showLoginDialog();
			} catch (Exception e) {
				this.exceptionHandler(e, getBundleString("load_cache_entries_list_error_summary"));
			}
		}
	}
	
	public void init(TOCache toCache) {
		this.toCache = toCache;
	}
	
	public void onRequestReloadDatatable() {
		loadCacheEntriesList();
	}
	
	public void onInvalidateKey(TOCacheEntry item) {
		try {
			viewModel.clearCacheWithKey(this.toCache.getName(), item.getKey());
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getBundleString("invalidate_key_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getBundleString("invalidate_key_error_summary"));
		}
	}

	public String getTitle() {
		return this.toCache.getName();
	}
	
	public TOCache getToCache() {
		return toCache;
	}

	public void setToCache(TOCache toCache) {
		this.toCache = toCache;
	}

	public List<TOCacheEntry> getEntriesList() {
		return entriesList;
	}

	public void setEntriesList(List<TOCacheEntry> entriesList) {
		this.entriesList = entriesList;
	}
	
}

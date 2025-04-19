package br.com.administrator.managedbean.cache;

import br.com.administrator.managedbean.common.beans.AbstractSearchMBean;
import br.com.administrator.to.TOCache;
import br.com.administrator.to.TOCacheEntry;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.utils.StringUtils;
import br.com.administrator.viewmodel.cache.CacheDialogViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("cacheDialogMBean")
@ViewScoped
public class CacheDialogMBean extends AbstractSearchMBean<TOCacheEntry> {

	private static final long serialVersionUID = 1L;

	private TOCache toCache;
	
	@Inject
	private CacheDialogViewModel viewModel;

	@Override
	public void onInit() {
		this.toCache = new TOCache();
	}
	
	public void init(TOCache toCache) {
		this.toCache = toCache;
	}
	
	private void loadCacheEntriesList() {
		if (StringUtils.isNotNull(this.toCache.getName())) {
			try {
				setData(viewModel.getListCacheEntries(this.toCache.getName()));
			} catch(ExpiredTokenException | NotFoundTokenException exception) {
				exceptionHandler(exception, getSearchBundleString("search_error_summary"));
				showLoginDialog();
			} catch (Exception e) {
				this.exceptionHandler(e, getSearchBundleString("search_error_summary"));
			}
		}
	}
	
	public void onInvalidateKey(TOCacheEntry item) {
		try {
			viewModel.clearCacheWithKey(this.toCache.getName(), item.getKey());
			
			FacesUtils.addSucccessMessage("cacheDialogMessages",
										  getScreenBundleString("clear_cache_key_success_message", item.getKey()),
				  						  getScreenBundleString("caches_success_summary"));
			
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("invalidate_key_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getScreenBundleString("invalidate_key_error_summary"));
		}
	}
	
	public String getMessageConfirmationEntryCacheInvalidation(TOCacheEntry to) {
		return getScreenBundleString("message_dialog_confirmation_invalidation_cache_entry", to.getKey());
	}
	
	@Override
	public void onRequestReloadDatatable() {
		loadCacheEntriesList();
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.cache.cache_dialog";
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

}

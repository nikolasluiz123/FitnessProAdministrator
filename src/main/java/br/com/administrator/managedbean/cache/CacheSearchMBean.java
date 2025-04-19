package br.com.administrator.managedbean.cache;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractSearchMBean;
import br.com.administrator.to.TOCache;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.cache.CacheSearchViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("cacheSearchMBean")
@ViewScoped
public class CacheSearchMBean extends AbstractSearchMBean<TOCache> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CacheSearchViewModel viewModel;
	
	@Inject 
	private CacheDialogMBean cacheDialogMBean;

	private void loadCacheList() {
		try {
			setData(viewModel.getListCache());
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getSearchBundleString("search_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getSearchBundleString("search_error_summary"));
		}
	}
	
	@Override
	public void onRowSelect(SelectEvent<TOCache> event) {
		cacheDialogMBean.init(event.getObject());
	}
	
	@Override
	public void onRequestReloadDatatable() {
		loadCacheList();
	}
	
	public void onInvalidateAllCachesClick() {
		try {
			viewModel.clearAllCaches();
			
			FacesUtils.addSucccessMessage(getScreenBundleString("clear_all_caches_success_message"),
										  getScreenBundleString("caches_success_summary"));
			
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("clear_all_caches_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getScreenBundleString("clear_all_caches_error_summary"));
		}
	}
	
	public void onInvalidateCache(TOCache item) {
		try {
			viewModel.clearCacheWithName(item.getName());
			
			FacesUtils.addSucccessMessage(getScreenBundleString("clear_cache_success_message", item.getName()),
				  						  getScreenBundleString("caches_success_summary"));
			
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("clear_cache_with_name_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getScreenBundleString("clear_cache_with_name_error_summary"));
		}
	}
	
	public String getMessageConfirmationCacheInvalidation(TOCache to) {
		return getScreenBundleString("message_dialog_confirmation_invalidation_cache", to.getName());
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.cache.cache_search";
	}

}

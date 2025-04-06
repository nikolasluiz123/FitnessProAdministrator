package br.com.administrator.managedbean.cache;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOCache;
import br.com.administrator.viewmodel.cache.CacheSearchViewModel;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("cacheSearchMBean")
@ViewScoped
public class CacheSearchMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CacheSearchViewModel viewModel;
	
	@Inject 
	private CacheDialogMBean cacheDialogMBean;

	private List<TOCache> cacheList;
	
	@PostConstruct
	public void init() {
		this.cacheList = new ArrayList<>();
		
		loadCacheList();
	}

	private void loadCacheList() {
		try {
			this.cacheList = viewModel.getListCache();
		} catch (Exception e) {
			this.exceptionHandler(e, getBundleString("load_cache_list_error_summary"));
		}
	}
	
	public void onRowSelect(SelectEvent<TOCache> event) {
		cacheDialogMBean.init(event.getObject());
	}
	
	public void onRequestReloadDatatable() {
		loadCacheList();
	}
	
	public void onInvalidateAllCachesClick() {
		try {
			viewModel.clearAllCaches();
		} catch (Exception e) {
			this.exceptionHandler(e, getBundleString("clear_all_caches_error_summary"));
		}
	}
	
	public void onInvalidateCache(TOCache item) {
		try {
			viewModel.clearCacheWithName(item.getName());
		} catch (Exception e) {
			this.exceptionHandler(e, getBundleString("clear_cache_with_name_error_summary"));
		}
	}

	public List<TOCache> getCacheList() {
		return cacheList;
	}

	public void setCacheList(List<TOCache> cacheList) {
		this.cacheList = cacheList;
	}

	@Override
	protected String getBundleFileName() {
		return "cache_search";
	}

}

package br.com.administrator.managedbean.common.beans;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.managedbean.common.lazymodel.LazyDataModelCallback;
import br.com.administrator.to.common.AbstractModelTO;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;

@SuppressWarnings("serial")
public abstract class AbstractPagingSearchMBean<TO extends AbstractModelTO, LazyModel extends AbstractLazyDataModel<TO>> extends AbstractBaseMBean implements ISearchMBean<TO> {

	private boolean filterVisible;
	
	public abstract LazyModel getLazyModel();
	
	@PostConstruct
	public void init() {
		onInit();
		getLazyModel().setCallback(new DefaultLazyDataModelCallback());
	}
	
	@Override
	public void onRowSelect(SelectEvent<TO> event) {
		
	}
	
	@Override
	public void onInit() {
		
	}
	
	@Override
	public Boolean getFilterVisible() {
		return this.filterVisible;
	}
	
	@Override
	public void onToggleFilter() {
		this.filterVisible = !this.filterVisible;
	}
	
	@Override
	public String getEmptyMessage() {
		return getSearchBundleString("empty_table_message");
	}
	
	@Override
	public void onRequestReloadDatatable() {
		getLazyModel().reloadPreservingPagingState();
	}
	
	public class DefaultLazyDataModelCallback implements LazyDataModelCallback {

		@Override
		public void onException(Exception exception) {
			exceptionHandler(exception, getSearchBundleString("search_error_summary"));

			if (exception instanceof ExpiredTokenException || exception instanceof NotFoundTokenException) {
				showLoginDialog();
			}
		}
	}
	
}

package br.com.administrator.managedbean.common.beans;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.lazymodel.AbstractLazyDataModel;
import br.com.administrator.managedbean.lov.ILovCallbacks;
import br.com.administrator.to.common.AbstractModelTO;

@SuppressWarnings("serial")
public abstract class AbstractPagingLovMBean<TO extends AbstractModelTO, LazyModel extends AbstractLazyDataModel<TO>>
		extends AbstractPagingSearchMBean<TO, LazyModel> implements ILovMBean<TO> {

	private ILovCallbacks<TO> callback;

	@Override
	public ILovCallbacks<TO> getCallback() {
		return callback;
	}

	@Override
	public void setCallback(ILovCallbacks<TO> callback) {
		this.callback = callback;
	}

	@Override
	public void onRowSelect(SelectEvent<TO> event) {
		this.callback.onItemSelected(event.getObject());
	}

}

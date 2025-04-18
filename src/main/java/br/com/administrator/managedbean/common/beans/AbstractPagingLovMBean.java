package br.com.administrator.managedbean.common.beans;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.lov.ILovCallbacks;
import br.com.administrator.to.common.AbstractModelTO;

@SuppressWarnings("serial")
public abstract class AbstractPagingLovMBean<T extends AbstractModelTO> extends AbstractPagingSearchMBean implements ILovMBean<T> {

	private ILovCallbacks<T> callback;

	@Override
	public ILovCallbacks<T> getCallback() {
		return callback;
	}

	@Override
	public void setCallback(ILovCallbacks<T> callback) {
		this.callback = callback;
	}
	
	@Override
	public void onRowSelect(SelectEvent<T> event) {
		this.callback.onItemSelected(event.getObject());
	}
	
}

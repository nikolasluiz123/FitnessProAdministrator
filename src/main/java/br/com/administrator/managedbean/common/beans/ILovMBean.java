package br.com.administrator.managedbean.common.beans;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.lov.ILovCallbacks;
import br.com.administrator.to.common.AbstractModelTO;

public interface ILovMBean<T extends AbstractModelTO> {

	ILovCallbacks<T> getCallback();

	void setCallback(ILovCallbacks<T> callback);
	
	void onRowSelect(SelectEvent<T> event);
}

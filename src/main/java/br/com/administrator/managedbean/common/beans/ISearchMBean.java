package br.com.administrator.managedbean.common.beans;

import org.primefaces.event.SelectEvent;

import br.com.administrator.to.common.AbstractModelTO;

public interface ISearchMBean<TO extends AbstractModelTO> {

	public void onRowSelect(SelectEvent<TO> event);
	
	public void onRequestReloadDatatable();
	
	public void onInit();
	
	public Boolean getFilterVisible();
	
	public void onToggleFilter();
	
	public String getEmptyMessage();
}

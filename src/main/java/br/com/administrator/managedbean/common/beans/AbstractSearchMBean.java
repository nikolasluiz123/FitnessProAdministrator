package br.com.administrator.managedbean.common.beans;

import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.administrator.to.common.AbstractModelTO;
import jakarta.annotation.PostConstruct;

@SuppressWarnings("serial")
public abstract class AbstractSearchMBean<TO extends AbstractModelTO> extends AbstractBaseMBean implements ISearchMBean<TO> {

	private List<TO> data;
	private boolean filterVisible;
	
	@PostConstruct
	public void init() {
		onInit();
		onRequestReloadDatatable();
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

	public List<TO> getData() {
		return data;
	}

	public void setData(List<TO> data) {
		this.data = data;
	}

}

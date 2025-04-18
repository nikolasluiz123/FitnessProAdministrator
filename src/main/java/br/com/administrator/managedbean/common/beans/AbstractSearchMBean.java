package br.com.administrator.managedbean.common.beans;

import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.administrator.to.common.AbstractModelTO;
import jakarta.annotation.PostConstruct;

@SuppressWarnings("serial")
public abstract class AbstractSearchMBean<TO extends AbstractModelTO> extends AbstractBaseMBean implements ISearchMBean<TO> {

	private List<TO> data;
	
	@PostConstruct
	public void init() {
		onRequestReloadDatatable();
	}
	
	@Override
	public void onRowSelect(SelectEvent<TO> event) {

	}

	public List<TO> getData() {
		return data;
	}

	public void setData(List<TO> data) {
		this.data = data;
	}

}

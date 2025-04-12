package br.com.administrator.managedbean.academy;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOAcademy;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("academySearchMBean")
@ViewScoped
public class AcademySearchMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyAcademyDataModel lazyModel;

	@Inject
	private AcademyDialogMBean academyDialogMBean;

	@PostConstruct
	public void init() {
		this.lazyModel.setCallback(new DefaultLazyDataModelCallback());
	}
	
	public LazyAcademyDataModel getLazyModel() {
		return lazyModel;
	}

	public void onNewAcademyClick() {
		academyDialogMBean.init();
	}

	public void onRowSelect(SelectEvent<TOAcademy> event) {
		academyDialogMBean.init(event.getObject());
	}

	public void onRequestReloadDatatable() {
		lazyModel.reloadPreservingPagingState();
	}

	@Override
	protected String getBundleFileName() {
		return "academy_search";
	}

}

package br.com.administrator.managedbean.academy;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.academy.lazymodel.LazyAcademyDataModel;
import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.to.TOAcademy;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("academySearchMBean")
@ViewScoped
public class AcademySearchMBean extends AbstractPagingSearchMBean<TOAcademy, LazyAcademyDataModel> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyAcademyDataModel lazyModel;

	@Inject
	private AcademyDialogMBean academyDialogMBean;

	public void onNewAcademyClick() {
		academyDialogMBean.init();
	}
	
	@Override
	public LazyAcademyDataModel getLazyModel() {
		return lazyModel;
	}

	@Override
	public void onRowSelect(SelectEvent<TOAcademy> event) {
		academyDialogMBean.init(event.getObject());
	}

	@Override
	protected String getScreenBundleFilePath() {
		return "messages.academy.academy_search";
	}

}

package br.com.administrator.managedbean.academy;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("academySearchMBean")
@ViewScoped
public class AcademySearchMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyAcademyDataModel lazyModel;

	public LazyAcademyDataModel getLazyModel() {
		return lazyModel;
	}
	
	public void onNewAcademyClick() {
		
	}

	@Override
	protected String getBundleFileName() {
		return "academy_search";
	}
	
}

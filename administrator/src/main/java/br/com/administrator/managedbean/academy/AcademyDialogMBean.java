package br.com.administrator.managedbean.academy;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOAcademy;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.AcademyDialogViewModel;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("academyDialogMBean")
@ViewScoped
public class AcademyDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOAcademy toAcademy;
	
	@Inject
	private AcademyDialogViewModel viewModel;	
	
	@PostConstruct
	public void init() {
		this.toAcademy = new TOAcademy();
	}
	
	public void init(TOAcademy to) {
		this.toAcademy = to;
	}

	public void onAcademySaveClick() {
		try {
			viewModel.saveAcademy(toAcademy);
			
			FacesUtils.addSucccessMessage(getBundleString("save_academy_success", toAcademy.getName()), 
										  getBundleString("save_success_summary"));
		} catch (Exception exception) {
			exceptionHandler(exception, getBundleString("save_error_summary"));
		}
	}
	
	public String getTitle() {
		if (toAcademy.getId() != null) {
			return getBundleString("label_title_academy", toAcademy.getName());
		} else {
			return getBundleString("label_title_new_academy");
		}
	}

	public TOAcademy getToAcademy() {
		return toAcademy;
	}

	public void setToAcademy(TOAcademy toAcademy) {
		this.toAcademy = toAcademy;
	}

	@Override
	protected String getBundleFileName() {
		return "academy_dialog";
	}

}

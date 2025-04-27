package br.com.administrator.managedbean.executionlogs;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.administrator.utils.StringUtils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("logPackageDialogMBean")
@ViewScoped
public class LogPackageDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOExecutionLogPackage toExecutionLogPackage;

	@PostConstruct
	public void init() {
		this.toExecutionLogPackage = new TOExecutionLogPackage();
	}

	public void init(TOExecutionLogPackage to) {
		this.toExecutionLogPackage = to;
	}

	public TOExecutionLogPackage getToExecutionLogPackage() {
		return toExecutionLogPackage;
	}

	public void setToExecutionLogPackage(TOExecutionLogPackage toExecutionLogPackage) {
		this.toExecutionLogPackage = toExecutionLogPackage;
	}
	
	public boolean isVisibleImportInfos() {
		return this.toExecutionLogPackage.getInsertedItemsCount() != null ||
				this.toExecutionLogPackage.getUpdatedItemsCount() != null;
	}
	
	public boolean isVisibleExportInfos() {
		return this.toExecutionLogPackage.getAllItemsCount() != null;
	}
	
	public boolean isVisibleRequestBody() {
		return StringUtils.isNotNull(this.toExecutionLogPackage.getRequestBody());
	}
	
	public boolean isVisibleResponseBody() {
		return StringUtils.isNotNull(this.toExecutionLogPackage.getResponseBody());
	}
	
	public boolean isVisibleError() {
		return StringUtils.isNotNull(this.toExecutionLogPackage.getError());
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.logs.log_package_dialog";
	}
}

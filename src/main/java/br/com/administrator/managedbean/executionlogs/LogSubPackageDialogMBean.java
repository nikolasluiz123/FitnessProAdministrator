package br.com.administrator.managedbean.executionlogs;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOExecutionLogSubPackage;
import br.com.administrator.utils.StringUtils;
import br.com.administrator.utils.UnityFormatterUtil;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("logSubPackageDialogMBean")
@ViewScoped
public class LogSubPackageDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOExecutionLogSubPackage toExecutionLogSubPackage;

	public void init(TOExecutionLogSubPackage to) {
		this.toExecutionLogSubPackage = to;
	}

	public TOExecutionLogSubPackage getToExecutionLogSubPackage() {
		return toExecutionLogSubPackage;
	}

	public void setToExecutionLogSubPackage(TOExecutionLogSubPackage toExecutionLogSubPackage) {
		this.toExecutionLogSubPackage = toExecutionLogSubPackage;
	}

	public boolean isVisibleRequestBody() {
		return StringUtils.isNotNull(this.toExecutionLogSubPackage.getRequestBody());
	}

	public boolean isVisibleResponseBody() {
		return StringUtils.isNotNull(this.toExecutionLogSubPackage.getResponseBody());
	}

	public String getExecutionSize() {
		return UnityFormatterUtil.formatKilobytes(this.toExecutionLogSubPackage.getKbSize());
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.logs.log_sub_package_dialog";
	}
}
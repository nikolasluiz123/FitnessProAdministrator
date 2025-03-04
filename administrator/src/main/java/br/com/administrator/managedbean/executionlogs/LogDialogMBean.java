package br.com.administrator.managedbean.executionlogs;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.viewmodel.log.LogDialogViewModel;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("logDialogMBean")
@ViewScoped
public class LogDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOExecutionLog toExecutionLog;
	
	@Inject
	private LogDialogViewModel viewModel;	
	
	@PostConstruct
	public void init() {
		this.toExecutionLog = new TOExecutionLog();
	}
	
	public void init(TOExecutionLog to) {
		this.toExecutionLog = to;
	}
	

	@Override
	protected String getBundleFileName() {
		return "log_dialog";
	}

	public TOExecutionLog getToExecutionLog() {
		return toExecutionLog;
	}

	public void setToExecutionLog(TOExecutionLog toExecutionLog) {
		this.toExecutionLog = toExecutionLog;
	}
	
}

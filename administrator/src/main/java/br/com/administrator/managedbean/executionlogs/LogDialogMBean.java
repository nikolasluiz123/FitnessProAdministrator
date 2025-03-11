package br.com.administrator.managedbean.executionlogs;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.to.TOExecutionLogPackage;
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
	private LazyExecutionLogDataModel lazyModel;
	
	@Inject
	private LogPackageDialogMBean logPackageDialog;
	
	@PostConstruct
	public void init() {
		this.toExecutionLog = new TOExecutionLog();
	}
	
	public void init(TOExecutionLog to) {
		this.toExecutionLog = to;
	}
	
	public void onRowSelect(SelectEvent<TOExecutionLogPackage> event) {
		logPackageDialog.init(event.getObject());
	}

	public void onRequestReloadDatatable() {
		lazyModel.reloadPreservingPagingState();
	}
	
	public boolean isVisibleUserEmail() {
		return this.toExecutionLog.getUserEmail() != null;
	}

	public boolean isVisiblePageSize() {
		return this.toExecutionLog.getPageSize() != null;
	}
	
	public boolean isVisibleLastUpdateDate() {
		return this.toExecutionLog.getLastUpdateDate() != null;
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

package br.com.administrator.managedbean.executionlogs;

import static br.com.fitnesspro.models.executions.enums.EnumExecutionType.EXPORTATION;
import static br.com.fitnesspro.models.executions.enums.EnumExecutionType.IMPORTATION;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("logDialogMBean")
@ViewScoped
public class LogDialogMBean extends AbstractPagingSearchMBean {

	private static final long serialVersionUID = 1L;

	private TOExecutionLog toExecutionLog;
	
	@Inject
	private LazyExecutionLogPackageDataModel lazyModel;
	
	@Inject
	private LogPackageDialogMBean logPackageDialog;
	
	@PostConstruct
	public void init() {
		this.toExecutionLog = new TOExecutionLog();
		this.lazyModel.setCallback(new DefaultLazyDataModelCallback());
	}
	
	public void init(TOExecutionLog to) {
		this.toExecutionLog = to;
		
		lazyModel.setExecutionLogId(to.getId());
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

	
	public boolean isVisibleLastUpdateDate() {
		return this.toExecutionLog.getLastUpdateDate() != null;
	}
	
	public boolean isVisibleImportExportInfos() {
		EnumExecutionType executionType = this.toExecutionLog.getType().getValue();
		
		return executionType == EXPORTATION || executionType == IMPORTATION;
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

	public LazyExecutionLogPackageDataModel getLazyModel() {
		return lazyModel;
	}

}

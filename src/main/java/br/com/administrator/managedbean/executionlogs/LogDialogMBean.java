package br.com.administrator.managedbean.executionlogs;

import static br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType.EXPORTATION;
import static br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType.IMPORTATION;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.managedbean.executionlogs.lazymodel.LazyExecutionLogPackageDataModel;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.administrator.utils.TimeConverterUtil;
import br.com.administrator.utils.UnityFormatterUtil;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("logDialogMBean")
@ViewScoped
public class LogDialogMBean extends AbstractPagingSearchMBean<TOExecutionLogPackage, LazyExecutionLogPackageDataModel> {

	private static final long serialVersionUID = 1L;

	private TOExecutionLog toExecutionLog;
	
	@Inject
	private LazyExecutionLogPackageDataModel lazyModel;
	
	@Inject
	private LogPackageDialogMBean logPackageDialog;
	
	@Override
	public void onInit() {
		this.toExecutionLog = new TOExecutionLog();
	}
	
	public void init(TOExecutionLog to) {
		this.toExecutionLog = to;
		lazyModel.setExecutionLogId(to.getId());
	}
	
	@Override
	public void onRowSelect(SelectEvent<TOExecutionLogPackage> event) {
		logPackageDialog.init(event.getObject());
	}

	public boolean isVisibleUserEmail() {
		return this.toExecutionLog.getUserEmail() != null;
	}
	
	public boolean isVisibleImportExportInfos() {
		EnumExecutionType executionType = this.toExecutionLog.getType().getValue();
		
		return executionType == EXPORTATION || executionType == IMPORTATION;
	}

	public String getExecutionSize() {
		return UnityFormatterUtil.formatKilobytes(this.toExecutionLog.getKbSize());
	}
	
	public String getServiceProcessingDuration() {
		return TimeConverterUtil.formatDuration(this.toExecutionLog.getServiceProcessingDuration());
	}
	
	public String getClientProcessingDuration() {
		return TimeConverterUtil.formatDuration(this.toExecutionLog.getClientProcessingDuration());
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.logs.log_dialog";
	}

	@Override
	public LazyExecutionLogPackageDataModel getLazyModel() {
		return lazyModel;
	}

	public TOExecutionLog getToExecutionLog() {
		return toExecutionLog;
	}

	public void setToExecutionLog(TOExecutionLog toExecutionLog) {
		this.toExecutionLog = toExecutionLog;
	}

}

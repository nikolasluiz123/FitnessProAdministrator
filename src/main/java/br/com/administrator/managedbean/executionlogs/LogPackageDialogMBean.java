package br.com.administrator.managedbean.executionlogs;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.managedbean.executionlogs.lazymodel.LazyExecutionLogSubPackageDataModel;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.administrator.to.TOExecutionLogSubPackage;
import br.com.administrator.utils.StringUtils;
import br.com.administrator.utils.TimeConverterUtil;
import br.com.administrator.utils.UnityFormatterUtil;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("logPackageDialogMBean")
@ViewScoped
public class LogPackageDialogMBean extends AbstractPagingSearchMBean<TOExecutionLogSubPackage, LazyExecutionLogSubPackageDataModel> {

	private static final long serialVersionUID = 1L;

	private TOExecutionLogPackage toExecutionLogPackage;
	
	@Inject
	private LazyExecutionLogSubPackageDataModel lazyModel;

	@Override
	public void onInit() {
		this.toExecutionLogPackage = new TOExecutionLogPackage();
	}

	public void init(TOExecutionLogPackage to) {
		this.toExecutionLogPackage = to;
		this.lazyModel.setExecutionLogPackageId(to.getId());
	}

	public TOExecutionLogPackage getToExecutionLogPackage() {
		return toExecutionLogPackage;
	}

	public void setToExecutionLogPackage(TOExecutionLogPackage toExecutionLogPackage) {
		this.toExecutionLogPackage = toExecutionLogPackage;
	}
	
	@Override
	public void onRowSelect(SelectEvent<TOExecutionLogSubPackage> event) {
		
	}
	
	public boolean isVisibleImportExportInfos() {
		return isVisibleImportInfos() || isVisibleExportInfos();
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
	
	public boolean isVisibleExecutionAdditionalInfos() {
		return StringUtils.isNotNull(this.toExecutionLogPackage.getExecutionAdditionalInfos());
	}
	
	public String getExecutionSize() {
		return getExecutionSize(this.toExecutionLogPackage.getKbSize());
	}
	
	public String getServiceProcessingDuration() {
		return getServiceProcessingDuration(this.toExecutionLogPackage.getServiceProcessingDuration());
	}
	
	public String getClientProcessingDuration() {
		return getClientProcessingDuration(this.toExecutionLogPackage.getClientProcessingDuration());
	}
	
	public String getExecutionSize(Long value) {
		return UnityFormatterUtil.formatKilobytes(value);
	}
	
	public String getServiceProcessingDuration(Long value) {
		return TimeConverterUtil.formatDuration(value);
	}
	
	public String getClientProcessingDuration(Long value) {
		return TimeConverterUtil.formatDuration(value);
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.logs.log_package_dialog";
	}

	@Override
	public LazyExecutionLogSubPackageDataModel getLazyModel() {
		return lazyModel;
	}
}
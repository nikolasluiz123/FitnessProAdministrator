package br.com.administrator.to;

import java.time.LocalDateTime;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.to.common.AbstractModelTO;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType;

public class TOExecutionLog extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private LabeledEnum<EnumExecutionType> type;
	private LabeledEnum<EnumExecutionState> state;
	private String endpoint;
	private String methodName;
	private String userEmail;
	private String deviceId;
	private String applicationName;
	private Integer pageSize;
	private LocalDateTime lastUpdateDate;
	private LocalDateTime creationDate;

	public LabeledEnum<EnumExecutionType> getType() {
		return type;
	}

	public void setType(LabeledEnum<EnumExecutionType> type) {
		this.type = type;
	}

	public LabeledEnum<EnumExecutionState> getState() {
		return state;
	}

	public void setState(LabeledEnum<EnumExecutionState> state) {
		this.state = state;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

}

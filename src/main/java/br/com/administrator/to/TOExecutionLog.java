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
	private LocalDateTime creationDate;
	private Integer insertedItems;
	private Integer updatedItems;
	private Integer allItemsCount;
	private Long kbSize;
	private Long serviceProcessingDuration;
	private Long clientProcessingDuration;

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

	public Integer getInsertedItems() {
		return insertedItems;
	}

	public void setInsertedItems(Integer insertedItems) {
		this.insertedItems = insertedItems;
	}

	public Integer getUpdatedItems() {
		return updatedItems;
	}

	public void setUpdatedItems(Integer updatedItems) {
		this.updatedItems = updatedItems;
	}

	public Integer getAllItemsCount() {
		return allItemsCount;
	}

	public void setAllItemsCount(Integer allItemsCount) {
		this.allItemsCount = allItemsCount;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getKbSize() {
		return kbSize;
	}

	public void setKbSize(Long kbSize) {
		this.kbSize = kbSize;
	}

	public Long getServiceProcessingDuration() {
		return serviceProcessingDuration;
	}

	public void setServiceProcessingDuration(Long serviceProcessingDuration) {
		this.serviceProcessingDuration = serviceProcessingDuration;
	}

	public Long getClientProcessingDuration() {
		return clientProcessingDuration;
	}

	public void setClientProcessingDuration(Long clientProcessingDuration) {
		this.clientProcessingDuration = clientProcessingDuration;
	}

}

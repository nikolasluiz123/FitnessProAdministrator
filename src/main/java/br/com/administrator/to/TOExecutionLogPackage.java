package br.com.administrator.to;

import java.time.LocalDateTime;

import br.com.administrator.to.common.AbstractModelTO;

public class TOExecutionLogPackage extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private LocalDateTime serviceExecutionStart;
	private LocalDateTime serviceExecutionEnd;
	private LocalDateTime clientExecutionStart;
	private LocalDateTime clientExecutionEnd;
	private String requestBody;
	private String responseBody;
	private String error;
	private String executionAdditionalInfos;
	private Integer insertedItemsCount;
	private Integer updatedItemsCount;
	private Integer allItemsCount;

	public LocalDateTime getServiceExecutionStart() {
		return serviceExecutionStart;
	}

	public void setServiceExecutionStart(LocalDateTime serviceExecutionStart) {
		this.serviceExecutionStart = serviceExecutionStart;
	}

	public LocalDateTime getServiceExecutionEnd() {
		return serviceExecutionEnd;
	}

	public void setServiceExecutionEnd(LocalDateTime serviceExecutionEnd) {
		this.serviceExecutionEnd = serviceExecutionEnd;
	}

	public LocalDateTime getClientExecutionStart() {
		return clientExecutionStart;
	}

	public void setClientExecutionStart(LocalDateTime clientExecutionStart) {
		this.clientExecutionStart = clientExecutionStart;
	}

	public LocalDateTime getClientExecutionEnd() {
		return clientExecutionEnd;
	}

	public void setClientExecutionEnd(LocalDateTime clientExecutionEnd) {
		this.clientExecutionEnd = clientExecutionEnd;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getInsertedItemsCount() {
		return insertedItemsCount;
	}

	public void setInsertedItemsCount(Integer insertedItemsCount) {
		this.insertedItemsCount = insertedItemsCount;
	}

	public Integer getUpdatedItemsCount() {
		return updatedItemsCount;
	}

	public void setUpdatedItemsCount(Integer updatedItemsCount) {
		this.updatedItemsCount = updatedItemsCount;
	}

	public Integer getAllItemsCount() {
		return allItemsCount;
	}

	public void setAllItemsCount(Integer allItemsCount) {
		this.allItemsCount = allItemsCount;
	}

	public String getExecutionAdditionalInfos() {
		return executionAdditionalInfos;
	}

	public void setExecutionAdditionalInfos(String executionAdditionalInfos) {
		this.executionAdditionalInfos = executionAdditionalInfos;
	}

}

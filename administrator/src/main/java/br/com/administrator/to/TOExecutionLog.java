package br.com.administrator.to;

import java.time.LocalDateTime;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.to.common.AbstractModelTO;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;

public class TOExecutionLog extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private LabeledEnum<EnumExecutionType> type;
	private LabeledEnum<EnumExecutionState> state;
	private LocalDateTime executionStart;
	private LocalDateTime executionEnd;
	private String endpoint;
	private String requestBody;
	private String error;
	private String methodName;

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

	public LocalDateTime getExecutionStart() {
		return executionStart;
	}

	public void setExecutionStart(LocalDateTime executionStart) {
		this.executionStart = executionStart;
	}

	public LocalDateTime getExecutionEnd() {
		return executionEnd;
	}

	public void setExecutionEnd(LocalDateTime executionEnd) {
		this.executionEnd = executionEnd;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}

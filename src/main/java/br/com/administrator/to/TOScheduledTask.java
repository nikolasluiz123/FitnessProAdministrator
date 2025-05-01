package br.com.administrator.to;

import java.time.LocalDateTime;

import br.com.administrator.to.common.AbstractAuditableTO;

public class TOScheduledTask extends AbstractAuditableTO {

	private static final long serialVersionUID = 1L;

	private String name;
	private Long intervalMillis;
	private LocalDateTime lastExecutionTime;
	private String handlerBeanName;
	private String configJson;
	private Boolean active;

	public TOScheduledTask() {
		this.active = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getIntervalMillis() {
		return intervalMillis;
	}

	public void setIntervalMillis(Long intervalMillis) {
		this.intervalMillis = intervalMillis;
	}

	public LocalDateTime getLastExecutionTime() {
		return lastExecutionTime;
	}

	public void setLastExecutionTime(LocalDateTime lastExecutionTime) {
		this.lastExecutionTime = lastExecutionTime;
	}

	public String getHandlerBeanName() {
		return handlerBeanName;
	}

	public void setHandlerBeanName(String handlerBeanName) {
		this.handlerBeanName = handlerBeanName;
	}

	public String getConfigJson() {
		return configJson;
	}

	public void setConfigJson(String configJson) {
		this.configJson = configJson;
	}
	
}

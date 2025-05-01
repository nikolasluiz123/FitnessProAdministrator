package br.com.administrator.to.common;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public abstract class AbstractAuditableTO extends AbstractModelTO {

	private LocalDateTime creationDate;
	private LocalDateTime updateDate;

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

}

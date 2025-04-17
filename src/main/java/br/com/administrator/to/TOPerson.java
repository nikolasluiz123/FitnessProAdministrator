package br.com.administrator.to;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.administrator.to.common.AbstractModelTO;

public class TOPerson extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private String name;
	private LocalDateTime creationDate;
	private LocalDateTime updateDate;
	private LocalDate birthDate;
	private TOUser toUser;
	private Boolean active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public TOUser getToUser() {
		return toUser;
	}

	public void setToUser(TOUser toUser) {
		this.toUser = toUser;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}

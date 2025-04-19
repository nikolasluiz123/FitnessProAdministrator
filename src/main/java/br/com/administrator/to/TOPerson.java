package br.com.administrator.to;

import java.time.LocalDate;

import br.com.administrator.to.common.AbstractAuditableDTO;

public class TOPerson extends AbstractAuditableDTO {

	private static final long serialVersionUID = 1L;

	private String name;
	private String phone;
	private LocalDate birthDate;
	private TOUser toUser;
	private Boolean active;

	public TOPerson() {
		this.toUser = new TOUser();
		this.active = true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

package br.com.administrator.to;

import br.com.administrator.to.common.AbstractAuditableDTO;

public class TOAcademy extends AbstractAuditableDTO {

	private static final long serialVersionUID = 1L;

	private String name;
	private String address;
	private String phone;
	private Boolean active;
	
	public TOAcademy() {
		this.active = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}

package br.com.administrator.to;

import br.com.administrator.to.common.AbstractAuditableDTO;

public class TODevice extends AbstractAuditableDTO {

	private static final long serialVersionUID = 1L;

	private String model;
	private Boolean active;

	public TODevice() {
		this.active = true;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}

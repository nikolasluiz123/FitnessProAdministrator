package br.com.administrator.to;

import br.com.administrator.to.common.AbstractModelTO;

public class TOApplication extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private String name;
	private Boolean active;

	public TOApplication() {
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

}

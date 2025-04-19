package br.com.administrator.to;

import br.com.administrator.to.common.AbstractModelTO;

public class TOCache extends AbstractModelTO {

	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getId() {
		return this.name;
	}

}

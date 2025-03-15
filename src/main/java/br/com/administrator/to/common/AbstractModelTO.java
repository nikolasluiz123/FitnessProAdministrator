package br.com.administrator.to.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractModelTO implements Serializable {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}

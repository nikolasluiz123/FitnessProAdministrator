package br.com.administrator.to;

import br.com.administrator.to.common.AbstractModelTO;

public class TOCacheEntry extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String getId() {
		return this.key;
	}
}

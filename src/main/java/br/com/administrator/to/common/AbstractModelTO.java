package br.com.administrator.to.common;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public abstract class AbstractModelTO implements Serializable {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		AbstractModelTO other = (AbstractModelTO) obj;
		return Objects.equals(id, other.id);
	}
}

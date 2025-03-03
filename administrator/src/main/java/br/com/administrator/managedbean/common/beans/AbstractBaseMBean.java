package br.com.administrator.managedbean.common.beans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import br.com.administrator.utils.FacesUtils;

@SuppressWarnings("serial")
public abstract class AbstractBaseMBean implements Serializable {

	protected abstract String getBundleFileName();
	
	protected String getBundleString(String key, Object...replaces) {
		String text = getBundleString(key);
		return MessageFormat.format(text, replaces);
	}
	
	protected String getBundleString(String key) {
		return getBundle().getString(key);
	}
	
	protected ResourceBundle getBundle() {
		return FacesUtils.getResourceBundle(getBundleFileName());
	}
	
	protected void exceptionHandler(Exception exception, String summary) {
		FacesUtils.addErrorMessage(exception.getMessage(), summary);
		exception.printStackTrace();
	}
}

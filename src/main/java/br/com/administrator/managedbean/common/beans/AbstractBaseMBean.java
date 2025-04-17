package br.com.administrator.managedbean.common.beans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.primefaces.PrimeFaces;

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
	
	protected void exceptionHandler(String clientId, Exception exception, String summary) {
		FacesUtils.addErrorMessage(clientId, exception.getMessage(), summary);
		exception.printStackTrace();
	}
	
	protected void exceptionHandler(Exception exception, String summary) {
		exceptionHandler(null, exception, summary);
	}
	
	protected void showLoginDialog() {
		PrimeFaces.current().executeScript("PF('loginDialogWV').show()");
	}
	
}

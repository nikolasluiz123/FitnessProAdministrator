package br.com.administrator.managedbean.common.beans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.primefaces.PrimeFaces;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.utils.FacesUtils;

@SuppressWarnings("serial")
public abstract class AbstractBaseMBean implements Serializable {

	protected abstract String getScreenBundleFilePath();
	
	protected String getScreenBundleString(String key, Object...replaces) {
		String text = getScreenBundleString(key);
		return MessageFormat.format(text, replaces);
	}
	
	protected String getScreenBundleString(String key) {
		return getScreenBundle().getString(key);
	}
	
	protected String getMaintenanceBundleString(String key, Object...replaces) {
		String text = getMaintenanceBundleString(key);
		return MessageFormat.format(text, replaces);
	}
	
	protected String getMaintenanceBundleString(String key) {
		return getMaintenanceBundle().getString(key);
	}
	
	protected String getSearchBundleString(String key, Object...replaces) {
		String text = getSearchBundleString(key);
		return MessageFormat.format(text, replaces);
	}
	
	protected String getSearchBundleString(String key) {
		return getSearchBundle().getString(key);
	}
	
	protected ResourceBundle getScreenBundle() {
		return FacesUtils.getResourceBundle(getScreenBundleFilePath());
	}
	
	protected ResourceBundle getMaintenanceBundle() {
		return FacesUtils.getResourceBundle(ICommonBundlePaths.MAINTENANCE_BEAN);
	}
	
	protected ResourceBundle getSearchBundle() {
		return FacesUtils.getResourceBundle(ICommonBundlePaths.SEARCH_BEAN);
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

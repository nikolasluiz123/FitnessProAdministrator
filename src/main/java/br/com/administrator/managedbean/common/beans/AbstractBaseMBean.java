package br.com.administrator.managedbean.common.beans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.primefaces.PrimeFaces;

import br.com.administrator.managedbean.common.lazymodel.LazyDataModelCallback;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;

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
	
	protected void showLoginDialog() {
		PrimeFaces.current().executeScript("PF('loginDialogWV').show()");
	}
	
	public class DefaultLazyDataModelCallback implements LazyDataModelCallback {
		
		@Override
		public void onException(Exception exception) {
			exceptionHandler(exception, getBundleString("lazy_data_model_error_summary"));
			
			if (exception instanceof ExpiredTokenException) {
				showLoginDialog();
			}
		}
	}
}

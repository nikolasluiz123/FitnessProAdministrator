package br.com.administrator.managedbean.token;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("secretKeyDialogMBean")
@ViewScoped
public class SecretKeyDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private String key;
	
	public void init(String key) {
		this.key = key;
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.token.secret_key_dialog";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}

package br.com.administrator.to;

import br.com.administrator.to.common.AbstractModelTO;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;

public class TOServiceTokenGeneration extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private EnumTokenType type;
	private TOUser selectedUser;
	private TODevice selectedDevice;
	private TOApplication selectedApplication;
	
	public TOServiceTokenGeneration() {
		this.type = EnumTokenType.APPLICATION_TOKEN;
		this.selectedApplication = new TOApplication();
		this.selectedDevice = new TODevice();
		this.selectedUser = new TOUser();
	}
	
	public EnumTokenType getType() {
		return type;
	}

	public void setType(EnumTokenType type) {
		this.type = type;
	}

	public TOUser getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(TOUser selectedUser) {
		this.selectedUser = selectedUser;
	}

	public TODevice getSelectedDevice() {
		return selectedDevice;
	}

	public void setSelectedDevice(TODevice selectedDevice) {
		this.selectedDevice = selectedDevice;
	}

	public TOApplication getSelectedApplication() {
		return selectedApplication;
	}

	public void setSelectedApplication(TOApplication selectedApplication) {
		this.selectedApplication = selectedApplication;
	}

}

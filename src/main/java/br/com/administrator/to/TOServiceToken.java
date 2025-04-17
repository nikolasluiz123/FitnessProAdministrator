package br.com.administrator.to;

import java.time.LocalDateTime;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.to.common.AbstractModelTO;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;

public class TOServiceToken extends AbstractModelTO {

	private static final long serialVersionUID = 1L;

	private String jwtToken;
	private LabeledEnum<EnumTokenType> type;
	private LocalDateTime creationDate;
	private LocalDateTime expirationDate;
	private TOUser toUser;
	private TODevice toDevice;
	private TOApplication toApplication;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public LabeledEnum<EnumTokenType> getType() {
		return type;
	}

	public void setType(LabeledEnum<EnumTokenType> type) {
		this.type = type;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public TOUser getToUser() {
		return toUser;
	}

	public void setToUser(TOUser toUser) {
		this.toUser = toUser;
	}

	public TODevice getToDevice() {
		return toDevice;
	}

	public void setToDevice(TODevice toDevice) {
		this.toDevice = toDevice;
	}

	public TOApplication getToApplication() {
		return toApplication;
	}

	public void setToApplication(TOApplication toApplication) {
		this.toApplication = toApplication;
	}

}

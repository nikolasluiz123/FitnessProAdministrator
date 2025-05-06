package br.com.administrator.to;

import br.com.administrator.to.common.AbstractAuditableTO;

public class TODevice extends AbstractAuditableTO {

	private static final long serialVersionUID = 1L;

	private String model;
	private String brand;
	private String androidVersion;
	private Boolean active;
	private String firebaseMessagingToken;
	private TOPerson person;

	public TODevice() {
		this.active = true;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFirebaseMessagingToken() {
		return firebaseMessagingToken;
	}

	public void setFirebaseMessagingToken(String firebaseMessagingToken) {
		this.firebaseMessagingToken = firebaseMessagingToken;
	}

	public TOPerson getPerson() {
		return person;
	}

	public void setPerson(TOPerson person) {
		this.person = person;
	}
	
}

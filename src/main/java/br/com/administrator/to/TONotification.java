package br.com.administrator.to;

import java.util.List;

public class TONotification {

	private String title;
	private String message;
	private List<String> devicesIds;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDevicesIds() {
		return devicesIds;
	}

	public void setDevicesIds(List<String> devicesIds) {
		this.devicesIds = devicesIds;
	}

}

package br.com.administrator.viewmodel.notification;

import java.io.Serializable;

import br.com.administrator.mappers.NotificationMapper;
import br.com.administrator.service.webclient.NotificationWebClient;
import br.com.administrator.to.TOGlobalNotification;
import br.com.administrator.to.TONotification;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class NotificationDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final NotificationWebClient webClient;
	private final NotificationMapper mapper;

	@Inject
	public NotificationDialogViewModel(NotificationWebClient webClient, NotificationMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
	}
	
	public void sendNotification(TONotification to) throws Exception {
		webClient.sendNotification(mapper.getNotificationDTOFrom(to));
	}

	public void sendGlobalNotification(TOGlobalNotification to) throws Exception {
		webClient.sendNotificationAllDevices(mapper.getGlobalNotificationDTOFrom(to));
	}

}

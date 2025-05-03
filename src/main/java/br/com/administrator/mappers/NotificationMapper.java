package br.com.administrator.mappers;

import org.modelmapper.ModelMapper;

import br.com.administrator.to.TOGlobalNotification;
import br.com.administrator.to.TONotification;
import br.com.fitnesspro.shared.communication.dtos.notification.GlobalNotificationDTO;
import br.com.fitnesspro.shared.communication.dtos.notification.NotificationDTO;

public final class NotificationMapper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public NotificationDTO getNotificationDTOFrom(TONotification toNotification) {
		return modelMapper.map(toNotification, NotificationDTO.class);
	}
	
	public TONotification getTONotificationFrom(NotificationDTO notificationDTO) {
		return modelMapper.map(notificationDTO, TONotification.class);
	}
	
	public GlobalNotificationDTO getGlobalNotificationDTOFrom(TOGlobalNotification toNotification) {
		return modelMapper.map(toNotification, GlobalNotificationDTO.class);
	}
	
	public TOGlobalNotification getTOGlobalNotificationFrom(GlobalNotificationDTO notificationDTO) {
		return modelMapper.map(notificationDTO, TOGlobalNotification.class);
	}
}

package br.com.administrator.service;

import br.com.fitnesspro.shared.communication.constants.EndPointsV1;
import br.com.fitnesspro.shared.communication.dtos.notification.GlobalNotificationDTO;
import br.com.fitnesspro.shared.communication.dtos.notification.NotificationDTO;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface INotificationService {

	public static final String NOTIFY_ALL = EndPointsV1.NOTIFICATION + EndPointsV1.NOTIFICATION_NOTIFY_ALL;
	
	@POST(EndPointsV1.NOTIFICATION)
	Call<FitnessProServiceResponse> sendNotification(@Header("Authorization") String token, @Body NotificationDTO notificationDTO);
	
	@POST(NOTIFY_ALL)
	Call<FitnessProServiceResponse> sendNotificationAllDevices(@Header("Authorization") String token, @Body GlobalNotificationDTO notificationDTO);

}

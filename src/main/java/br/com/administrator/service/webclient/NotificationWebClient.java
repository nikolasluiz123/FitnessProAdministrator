package br.com.administrator.service.webclient;

import java.net.ConnectException;

import br.com.administrator.service.INotificationService;
import br.com.administrator.service.exception.ServiceException;
import br.com.fitnesspro.shared.communication.dtos.notification.GlobalNotificationDTO;
import br.com.fitnesspro.shared.communication.dtos.notification.NotificationDTO;
import br.com.fitnesspro.shared.communication.responses.FitnessProServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import retrofit2.Call;

@ApplicationScoped
public class NotificationWebClient extends AbstractWebClient {
	
	private final INotificationService service;
	
	public NotificationWebClient() {
		this.service = null;
	}
	
	@Inject
	public NotificationWebClient(INotificationService service) {
		this.service = service;
	}
	
	public void sendNotification(NotificationDTO dto) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<FitnessProServiceResponse> serviceCall = service.sendNotification(token, dto);
			
			FitnessProServiceResponse response = getResponseBody(serviceCall);
			validateResponse(response);
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
	
	public void sendNotificationAllDevices(GlobalNotificationDTO dto) throws Exception {
		try {
			String token = getFormatedToken();
			
			Call<FitnessProServiceResponse> serviceCall = service.sendNotificationAllDevices(token, dto);
			
			FitnessProServiceResponse response = getResponseBody(serviceCall);
			validateResponse(response);
		} catch (ConnectException exception) {
			throw new ServiceException(getServiceBundleString("service_connection_error_message"), exception);
		}
	}
}

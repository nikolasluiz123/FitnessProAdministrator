package br.com.administrator.injection;

import br.com.administrator.service.IAcademyService;
import br.com.administrator.service.IApplicationService;
import br.com.administrator.service.IAuthenticationService;
import br.com.administrator.service.ICacheService;
import br.com.administrator.service.IDeviceService;
import br.com.administrator.service.IExecutionLogsService;
import br.com.administrator.service.INotificationService;
import br.com.administrator.service.IPersonService;
import br.com.administrator.service.IScheduledTaskService;
import br.com.administrator.service.ITokenService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import retrofit2.Retrofit;

@ApplicationScoped
public class RetrofitServiceProducer {
	
	@Produces
	public IAuthenticationService produceAuthenticationService(Retrofit retrofit) {
		return retrofit.create(IAuthenticationService.class);
	}
	
	@Produces
	public IExecutionLogsService produceExecutionLogsService(Retrofit retrofit) {
		return retrofit.create(IExecutionLogsService.class);
	}
	
	@Produces
	public IAcademyService produceAcademyService(Retrofit retrofit) {
		return retrofit.create(IAcademyService.class);
	}
	
	@Produces
	public ICacheService produceCacheService(Retrofit retrofit) {
		return retrofit.create(ICacheService.class);
	}
	
	@Produces
	public IApplicationService produceApplicationService(Retrofit retrofit) {
		return retrofit.create(IApplicationService.class);
	}
	
	@Produces
	public IDeviceService produceDeviceService(Retrofit retrofit) {
		return retrofit.create(IDeviceService.class);
	}
	
	@Produces
	public ITokenService produceTokenService(Retrofit retrofit) {
		return retrofit.create(ITokenService.class);
	}
	
	@Produces
	public IPersonService producePersonService(Retrofit retrofit) {
		return retrofit.create(IPersonService.class);
	}
	
	@Produces
	public IScheduledTaskService produceScheduledTaskService(Retrofit retrofit) {
		return retrofit.create(IScheduledTaskService.class);
	}
	
	@Produces
	public INotificationService produceNotificationService(Retrofit retrofit) {
		return retrofit.create(INotificationService.class);
	}
}

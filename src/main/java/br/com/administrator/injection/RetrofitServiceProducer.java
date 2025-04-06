package br.com.administrator.injection;

import br.com.administrator.service.IAcademyService;
import br.com.administrator.service.IAuthenticationService;
import br.com.administrator.service.ICacheService;
import br.com.administrator.service.IExecutionLogsService;
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

}

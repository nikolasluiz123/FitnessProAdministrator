package br.com.administrator.injection;

import br.com.administrator.service.IAuthenticationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import retrofit2.Retrofit;

@ApplicationScoped
public class RetrofitServiceProducer {
	
	@Produces
	public IAuthenticationService produceAuthenticationService(Retrofit retrofit) {
		return retrofit.create(IAuthenticationService.class);
	}

}

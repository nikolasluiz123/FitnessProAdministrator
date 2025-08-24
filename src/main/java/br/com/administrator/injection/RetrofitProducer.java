package br.com.administrator.injection;

import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.administrator.utils.ConfigProperties;
import br.com.fitnesspro.shared.communication.constants.Timeouts;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@ApplicationScoped
public class RetrofitProducer {
	
	 @Produces
     public Retrofit produceRetrofit(OkHttpClient client, ConfigProperties config) {
		Gson gson = GsonUtils.getDefaultGson();
		 
		String host = config.getEnvironmentPropertyValue("service.host");
		
        return new Retrofit.Builder()
             .baseUrl(host)
             .addConverterFactory(GsonConverterFactory.create(gson))
             .client(client)
             .build();
     }
	 
	@Produces
	public HttpLoggingInterceptor produceHttpLoggingInterceptor() {
		return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
	}
	
	@Produces
	public OkHttpClient produceOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
		return new OkHttpClient.Builder()
				.addInterceptor(httpLoggingInterceptor)
	            .callTimeout(Timeouts.OPERATION_VERY_LOW_TIMEOUT, TimeUnit.SECONDS)
	            .connectTimeout(Timeouts.OPERATION_VERY_LOW_TIMEOUT, TimeUnit.SECONDS)
	            .readTimeout(Timeouts.OPERATION_LOW_TIMEOUT, TimeUnit.SECONDS)
	            .writeTimeout(Timeouts.OPERATION_LOW_TIMEOUT, TimeUnit.SECONDS)
	            .build();
	}
	
	@Produces
	public ConfigProperties produceConfigProperties() throws Exception {
		return new ConfigProperties();
	}

}

package br.com.administrator.injection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.administrator.service.gson.adapter.LocalDateTimeTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalDateTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalTimeTypeAdapter;
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
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
				.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter()).create();
		 
		String host = config.getPropertyValue("service.host");
		
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
	            .callTimeout(Timeouts.OPERATION_HIGH_TIMEOUT, TimeUnit.SECONDS)
	            .connectTimeout(Timeouts.CONNECT_TIMEOUT, TimeUnit.SECONDS)
	            .readTimeout(Timeouts.OPERATION_MEDIUM_TIMEOUT, TimeUnit.SECONDS)
	            .writeTimeout(Timeouts.OPERATION_HIGH_TIMEOUT, TimeUnit.SECONDS)
	            .build();
	}
	
	@Produces
	public ConfigProperties produceConfigProperties() throws Exception {
		return new ConfigProperties();
	}

}

package br.com.administrator.injection;

import br.com.administrator.mappers.AcademyMapper;
import br.com.administrator.mappers.ApplicationMapper;
import br.com.administrator.mappers.CacheMapper;
import br.com.administrator.mappers.DeviceMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MapperProducer {
	
	@Produces
	public AcademyMapper producesAcademyMapper() {
		return new AcademyMapper();
	}
	
	@Produces
	public ApplicationMapper producesApplicationMapper() {
		return new ApplicationMapper();
	}
	
	@Produces
	public CacheMapper producesCacheMapper() {
		return new CacheMapper();
	}
	
	@Produces
	public DeviceMapper producesDeviceMapper() {
		return new DeviceMapper();
	}
}

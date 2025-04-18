package br.com.administrator.injection;

import br.com.administrator.mappers.AcademyMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MapperProducer {
	
	@Produces
	public AcademyMapper producesAcademyMapper() {
		return new AcademyMapper();
	}
}

package br.com.administrator.viewmodel.application;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.administrator.mappers.ApplicationMapper;
import br.com.administrator.service.webclient.ApplicationWebClient;
import br.com.administrator.to.TOApplication;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ApplicationSearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ApplicationWebClient webClient;
	private final ApplicationMapper mapper;

	@Inject
	public ApplicationSearchViewModel(ApplicationWebClient webClient, ApplicationMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
	}
	
	public List<TOApplication> getListApplication() throws Exception {
		List<ApplicationDTO> result = webClient.getListApplication();
		return result.stream().map(a -> mapper.getTOApplicationFrom(a)).collect(Collectors.toList());
	}
	
	public void inactivateApplication(TOApplication to) throws Exception {
		to.setActive(false);
		webClient.saveApplication(mapper.getApplicationDTOFrom(to));
	}

}

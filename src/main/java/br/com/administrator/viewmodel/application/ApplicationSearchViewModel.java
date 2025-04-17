package br.com.administrator.viewmodel.application;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.administrator.service.webclient.ApplicationWebClient;
import br.com.administrator.to.TOApplication;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ApplicationSearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ApplicationWebClient webClient;

	@Inject
	public ApplicationSearchViewModel(ApplicationWebClient webClient) {
        this.webClient = webClient;
	}
	
	public List<TOApplication> getListApplication() throws Exception {
		List<ApplicationDTO> result = webClient.getListApplication();
		return result.stream().map(this::getApplicationTO).collect(Collectors.toList());
	}
	
	public void inactivateApplication(TOApplication to) throws Exception {
		to.setActive(false);
		webClient.saveApplication(getApplicationDTO(to));
	}

	private TOApplication getApplicationTO(ApplicationDTO dto) {
		TOApplication to = new TOApplication();
		to.setId(dto.getId());
		to.setName(dto.getName());
		to.setActive(dto.getActive());
		
		return to;
	}
	
	private ApplicationDTO getApplicationDTO(TOApplication to) {
		ApplicationDTO dto = new ApplicationDTO();
		dto.setId(to.getId());
		dto.setActive(to.getActive());
		dto.setName(to.getName());
		
		return dto;
	}
}

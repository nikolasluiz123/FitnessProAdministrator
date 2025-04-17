package br.com.administrator.viewmodel.application;

import java.io.Serializable;

import br.com.administrator.service.webclient.ApplicationWebClient;
import br.com.administrator.to.TOApplication;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ApplicationDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ApplicationWebClient webClient;

	@Inject
	public ApplicationDialogViewModel(ApplicationWebClient webClient) {
		this.webClient = webClient;
	}
	
	public void saveApplication(TOApplication to) throws Exception {
		ApplicationDTO dto = getApplicationDTO(to);
		
		PersistenceServiceResponse response = webClient.saveApplication(dto);
		
		if (response.getSuccess()) {
			to.setId(response.getId());
		}
	}

	public void inactivateApplication(TOApplication to) throws Exception {
		to.setActive(false);
		webClient.saveApplication(getApplicationDTO(to));
	}
	
	private ApplicationDTO getApplicationDTO(TOApplication to) {
		ApplicationDTO dto = new ApplicationDTO();
		dto.setId(to.getId());
		dto.setActive(to.getActive());
		dto.setName(to.getName());
		
		return dto;
	}
}

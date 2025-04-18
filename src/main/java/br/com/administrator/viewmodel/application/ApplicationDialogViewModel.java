package br.com.administrator.viewmodel.application;

import java.io.Serializable;

import br.com.administrator.mappers.ApplicationMapper;
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
	private final ApplicationMapper mapper;

	@Inject
	public ApplicationDialogViewModel(ApplicationWebClient webClient, ApplicationMapper mapper) {
		this.webClient = webClient;
		this.mapper = mapper;
	}

	public void saveApplication(TOApplication to) throws Exception {
		ApplicationDTO dto = mapper.getApplicationDTOFrom(to);

		PersistenceServiceResponse<ApplicationDTO> response = webClient.saveApplication(dto);

		if (response.getSuccess()) {
			to.setId(response.getSavedDTO().getId());
		}
	}

	public void inactivateApplication(TOApplication to) throws Exception {
		to.setActive(false);
		webClient.saveApplication(mapper.getApplicationDTOFrom(to));
	}

}

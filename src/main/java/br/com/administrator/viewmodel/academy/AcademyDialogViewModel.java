package br.com.administrator.viewmodel.academy;

import java.io.Serializable;

import br.com.administrator.mappers.AcademyMapper;
import br.com.administrator.service.webclient.AcademyWebClient;
import br.com.administrator.to.TOAcademy;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class AcademyDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final AcademyWebClient webClient;
	private final AcademyMapper academyMapper;

	@Inject
	public AcademyDialogViewModel(AcademyWebClient webClient, AcademyMapper mapper) {
		this.webClient = webClient;
		this.academyMapper = mapper;
	}
	
	public void saveAcademy(TOAcademy to) throws Exception {
		AcademyDTO dto = academyMapper.getAcademyDTOFrom(to);
		
		PersistenceServiceResponse<AcademyDTO> response = webClient.saveAcademy(dto);
		
		if (response.getSuccess()) {
			to.setId(response.getSavedDTO().getId());
		}
	}

	public void inactivateAcademy(TOAcademy to) throws Exception {
		to.setActive(false);
		webClient.saveAcademy(academyMapper.getAcademyDTOFrom(to));
	}
}

package br.com.administrator.viewmodel.academy;

import java.io.Serializable;

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

	@Inject
	public AcademyDialogViewModel(AcademyWebClient webClient) {
		this.webClient = webClient;
	}
	
	public void saveAcademy(TOAcademy to) throws Exception {
		AcademyDTO dto = getAcademyDTO(to);
		
		PersistenceServiceResponse response = webClient.saveAcademy(dto);
		
		if (response.getSuccess()) {
			to.setId(response.getId());
		}
	}

	public void inactivateAcademy(TOAcademy to) throws Exception {
		to.setActive(false);
		webClient.saveAcademy(getAcademyDTO(to));
	}
	
	private AcademyDTO getAcademyDTO(TOAcademy to) {
		AcademyDTO dto = new AcademyDTO();
		dto.setId(to.getId());
		dto.setActive(to.getActive());
		dto.setAddress(to.getAddress());
		dto.setName(to.getName());
		dto.setPhone(to.getPhone());
		
		return dto;
	}
}

package br.com.administrator.viewmodel.person;

import java.io.Serializable;

import br.com.administrator.mappers.labeledenum.execution.UserLabeledEnumMapper;
import br.com.administrator.mappers.person.PersonMapper;
import br.com.administrator.service.webclient.PersonWebClient;
import br.com.administrator.to.TOPerson;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class PersonDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final PersonWebClient webClient;
	private final UserLabeledEnumMapper userLabeledMapper;
	private final PersonMapper mapper;

	@Inject
	public PersonDialogViewModel(PersonWebClient webClient, UserLabeledEnumMapper userLabeledMapper, PersonMapper mapper) {
		this.webClient = webClient;
		this.userLabeledMapper = userLabeledMapper;
		this.mapper = mapper;
	}

	public TOPerson savePerson(TOPerson to) throws Exception {
		PersonDTO dto = mapper.getPersonDTOFrom(to);

		PersistenceServiceResponse<PersonDTO> response = webClient.savePerson(dto);

		if (response.getSuccess()) {
			return mapper.getTOPersonFrom(response.getSavedDTO());
		} else {
			return null;
		}
	}

	public void inactivatePerson(TOPerson to) throws Exception {
		to.setActive(false);
		to.getUser().setActive(false);
		webClient.savePerson(mapper.getPersonDTOFrom(to));
	}

	public String getLabelUserType(EnumUserType type) {
		return userLabeledMapper.getLabelUserType(type);
	}
}

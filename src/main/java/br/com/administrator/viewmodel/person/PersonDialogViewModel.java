package br.com.administrator.viewmodel.person;

import java.io.Serializable;
import java.util.ResourceBundle;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.service.webclient.PersonWebClient;
import br.com.administrator.to.TOPerson;
import br.com.administrator.to.TOUser;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.UserDTO;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class PersonDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final PersonWebClient webClient;

	@Inject
	public PersonDialogViewModel(PersonWebClient webClient) {
		this.webClient = webClient;
	}

	public TOPerson savePerson(TOPerson to) throws Exception {
		PersonDTO dto = getPersonDTO(to);

		PersistenceServiceResponse<PersonDTO> response = webClient.savePerson(dto);

		if (response.getSuccess()) {
			return getPersonTO(response.getSavedDTO());
		} else {
			return null;
		}
	}

	public void inactivatePerson(TOPerson to) throws Exception {
		to.setActive(false);
		to.getToUser().setActive(false);
		webClient.savePerson(getPersonDTO(to));
	}

	private PersonDTO getPersonDTO(TOPerson to) {
		PersonDTO dto = new PersonDTO();
		dto.setId(to.getId());
		dto.setActive(to.getActive());
		dto.setName(to.getName());
		dto.setPhone(to.getPhone());
		dto.setBirthDate(to.getBirthDate());
		dto.setCreationDate(to.getCreationDate());
		dto.setUpdateDate(to.getUpdateDate());

		TOUser toUser = to.getToUser();

		if (toUser != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(toUser.getId());
			userDTO.setCreationDate(toUser.getCreationDate());
			userDTO.setUpdateDate(toUser.getUpdateDate());
			userDTO.setEmail(toUser.getEmail());
			userDTO.setPassword(toUser.getPassword());
			userDTO.setType(toUser.getType().getValue());
			userDTO.setActive(toUser.getActive());

			dto.setUser(userDTO);
		}

		return dto;
	}
	
	private TOPerson getPersonTO(PersonDTO dto) {
		TOPerson to = new TOPerson();
		to.setId(dto.getId());
		to.setCreationDate(dto.getCreationDate());
		to.setName(dto.getName());
		to.setUpdateDate(dto.getUpdateDate());
		to.setActive(dto.getActive());
		to.setBirthDate(dto.getBirthDate());
		to.setToUser(getTOUser(dto));
		
		return to;
	}

	private TOUser getTOUser(PersonDTO dto) {
		UserDTO userDTO = dto.getUser();
		TOUser toUser = new TOUser();
		toUser.setId(userDTO.getId());
		toUser.setCreationDate(userDTO.getCreationDate());
		toUser.setEmail(userDTO.getEmail());
		toUser.setPassword(userDTO.getPassword());
		toUser.setType(new LabeledEnum<EnumUserType>(userDTO.getType(), getLabelUserType(userDTO.getType())));
		toUser.setActive(userDTO.getActive());
		
		return toUser;
	}

	public String getLabelUserType(EnumUserType type) {
		ResourceBundle bundle = FacesUtils.getResourceBundle("lov_person");
		String label = null;

		switch (type) {
		case PERSONAL_TRAINER:
			label = bundle.getString("label_user_type_personal");
			break;
		case NUTRITIONIST:
			label = bundle.getString("label_user_type_nutri");
			break;
		case ACADEMY_MEMBER:
			label = bundle.getString("label_user_type_member");
			break;
		case ADMINISTRATOR:
			label = bundle.getString("label_user_type_admin");
			break;
		default:
			break;
		}

		return label;
	}
}

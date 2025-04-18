package br.com.administrator.managedbean.person;

import java.util.List;
import java.util.Optional;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.to.TOPerson;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.person.PersonDialogViewModel;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("personDialogMBean")
@ViewScoped
public class PersonDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOPerson toPerson;
	
	@Inject
	private PersonDialogViewModel viewModel;	
	
	private List<LabeledEnum<EnumUserType>> userTypes;
	private EnumUserType selectedUserType;
	
	@PostConstruct
	public void init() {
		this.toPerson = new TOPerson();
		this.userTypes = EnumUserType.getEntries().stream().map(x -> getLabeledType(x)).toList();
		this.toPerson.getToUser().setType(this.userTypes.get(0));
	}
	
	public void init(TOPerson to) {
		this.toPerson = to;
	}

	public void onPersonSaveClick() {
		try {
			this.toPerson = viewModel.savePerson(this.toPerson);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("save_person_success", this.toPerson.getName()), 
										  getScreenBundleString("save_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler("personDialogMessages", exception, getScreenBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler("personDialogMessages", exception, getScreenBundleString("save_error_summary"));
		}
	}
	
	public void onPersonInactivateClick( ) {
		try {
			viewModel.inactivatePerson(this.toPerson);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("inactivate_person_success", this.toPerson.getName()), 
				  						  getScreenBundleString("inactivation_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler("personDialogMessages", exception, getScreenBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getScreenBundleString("inactivation_error_summary"));
		}
	}
	
	public String getTitle() {
		if (this.toPerson.getId() != null) {
			return this.toPerson.getName();
		} else {
			return getScreenBundleString("label_title_new_person");
		}
	}
	
	public String getRequiredMessage(String fieldName) {
		return getScreenBundleString("message_required", fieldName);
	}
	
	public void onUserTypeChange() {
		Optional<LabeledEnum<EnumUserType>> value = userTypes.stream().filter(t -> t.getValue().equals(selectedUserType)).findFirst();
		this.toPerson.getToUser().setType(value.orElseGet(null));
	}
	
	private LabeledEnum<EnumUserType> getLabeledType(EnumUserType x) {
		return new LabeledEnum<EnumUserType>(x, viewModel.getLabelUserType(x));
	}
	
	public String getMessageConfirmationInactivation() {
		return getScreenBundleString("message_dialog_confirmation_inactivation", this.toPerson.getName());
	}

	public TOPerson getToPerson() {
		return toPerson;
	}

	public void setToPerson(TOPerson toPerson) {
		this.toPerson = toPerson;
	}

	public Boolean getDisabledDialog() {
		return this.toPerson.getId() != null && !this.toPerson.getActive();
	}
	
	public boolean isVisiblePhoneInput() {
		return this.toPerson.getToUser().getType().getValue() != EnumUserType.ACADEMY_MEMBER;
	}

	public List<LabeledEnum<EnumUserType>> getUserTypes() {
		return userTypes;
	}
	
	public EnumUserType getSelectedUserType() {
		return selectedUserType;
	}

	public void setSelectedUserType(EnumUserType selectedUserType) {
		this.selectedUserType = selectedUserType;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "person_dialog";
	}

}

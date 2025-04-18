package br.com.administrator.managedbean.person;

import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.to.TOPerson;
import br.com.administrator.viewmodel.person.PersonSearchViewModel;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("personSearchMBean")
@ViewScoped
public class PersonSearchMBean extends AbstractPagingSearchMBean<TOPerson, LazyPersonDataModel> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyPersonDataModel lazyModel;

	@Inject
	private PersonSearchViewModel viewModel;
	
	@Inject
	private PersonDialogMBean personDialogMBean;

	private List<LabeledEnum<EnumUserType>> userTypes;
	
	@PostConstruct
	public void init() {
		this.userTypes = EnumUserType.getEntries().stream().map(x -> getLabeledType(x)).toList();
	}
	
	public void onNewPersonClick() {
		this.personDialogMBean.init();
	}
	
	private LabeledEnum<EnumUserType> getLabeledType(EnumUserType x) {
		return new LabeledEnum<EnumUserType>(x, viewModel.getLabelUserType(x));
	}

	@Override
	public void onRowSelect(SelectEvent<TOPerson> event) {
		this.personDialogMBean.init(event.getObject());
	}
	
	@Override
	public LazyPersonDataModel getLazyModel() {
		return lazyModel;
	}

	public List<LabeledEnum<EnumUserType>> getUserTypes() {
		return userTypes;
	}

	@Override
	protected String getScreenBundleFilePath() {
		return "person_search";
	}
	
}

package br.com.administrator.managedbean.lov;

import java.util.List;

import br.com.administrator.managedbean.common.beans.AbstractPagingLovMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.managedbean.person.LazyPersonDataModel;
import br.com.administrator.to.TOPerson;
import br.com.administrator.viewmodel.person.PersonSearchViewModel;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("lovPersonMBean")
@ViewScoped
public class LovPersonMBean extends AbstractPagingLovMBean<TOPerson, LazyPersonDataModel> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LazyPersonDataModel lazyModel;

	@Inject
	private PersonSearchViewModel viewModel;

	private List<LabeledEnum<EnumUserType>> userTypes;

	@PostConstruct
	public void init() {
		this.userTypes = EnumUserType.getEntries().stream().map(x -> getLabeledType(x)).toList();
	}

	private LabeledEnum<EnumUserType> getLabeledType(EnumUserType x) {
		return new LabeledEnum<EnumUserType>(x, viewModel.getLabelUserType(x));
	}

	public List<LabeledEnum<EnumUserType>> getUserTypes() {
		return userTypes;
	}

	@Override
	public LazyPersonDataModel getLazyModel() {
		return lazyModel;
	}
	
	@Override
	protected String getScreenBundleFilePath() {
		return "lov_person";
	}

}

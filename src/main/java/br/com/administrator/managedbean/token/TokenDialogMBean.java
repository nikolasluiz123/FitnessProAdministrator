package br.com.administrator.managedbean.token;

import java.util.List;

import org.primefaces.PrimeFaces;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.managedbean.lov.ILovCallbacks;
import br.com.administrator.managedbean.lov.LovApplicationMBean;
import br.com.administrator.managedbean.lov.LovDeviceMBean;
import br.com.administrator.managedbean.lov.LovPersonMBean;
import br.com.administrator.to.TOApplication;
import br.com.administrator.to.TODevice;
import br.com.administrator.to.TOPerson;
import br.com.administrator.to.TOServiceTokenGeneration;
import br.com.administrator.to.TOUser;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.token.TokenDialogViewModel;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("tokenDialogMBean")
@ViewScoped
public class TokenDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOServiceTokenGeneration toServiceTokenGeneration;
	
	@Inject
	private TokenDialogViewModel viewModel;	
	
	@Inject
	private LovDeviceMBean lovDeviceMBean;
	
	@Inject
	private LovPersonMBean lovPersonMBean;
	
	@Inject 
	private LovApplicationMBean lovApplicationMBean;
	
	private List<LabeledEnum<EnumTokenType>> tokenTypes;

	@PostConstruct
	public void init() {
		this.toServiceTokenGeneration = new TOServiceTokenGeneration();
		this.tokenTypes = EnumTokenType.getEntries().stream().map(x -> getLabeledType(x)).toList();

		configureLovDeviceCallback();
		configureLovPersonCallback();
		configureLovApplicationCallback();
	}
	
	private LabeledEnum<EnumTokenType> getLabeledType(EnumTokenType x) {
		return new LabeledEnum<EnumTokenType>(x, viewModel.getLabelTokenType(x));
	}

	private void configureLovApplicationCallback() {
		this.lovApplicationMBean.setCallback(new ILovCallbacks<TOApplication>() {
			
			@Override
			public void onItemSelected(TOApplication value) {
				toServiceTokenGeneration.setSelectedApplication(value);
				updateApplicationLov();
			}
		});
	}
	
	private void configureLovPersonCallback() {
		this.lovPersonMBean.setCallback(new ILovCallbacks<TOPerson>() {
			
			@Override
			public void onItemSelected(TOPerson value) {
				toServiceTokenGeneration.setSelectedUser(value.getToUser());		
				updatePersonLov();
			}
		});
	}

	private void configureLovDeviceCallback() {
		this.lovDeviceMBean.setCallback(new ILovCallbacks<TODevice>() {
			
			@Override
			public void onItemSelected(TODevice value) {
				toServiceTokenGeneration.setSelectedDevice(value);
				updateDeviceLov();
			}
		});
	}
	
	private void updateApplicationLov() {
		PrimeFaces.current().ajax().update("tokenDialogForm:application:lovInput");
	}
	
	private void updatePersonLov() {
		PrimeFaces.current().ajax().update("tokenDialogForm:user:lovInput");
	}
	
	private void updateDeviceLov() {
		PrimeFaces.current().ajax().update("tokenDialogForm:device:lovInput");
	}
	
	public void onTokenGenerateClick() {
		try {
			viewModel.generateToken(toServiceTokenGeneration);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("generation_token_success"), getScreenBundleString("generation_token_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler("tokenDialogMessages", exception, getScreenBundleString("generation_token_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler("tokenDialogMessages", exception, getScreenBundleString("generation_token_error_summary"));
		}
	}
	
	public void onClearDevice() {
		this.toServiceTokenGeneration.setSelectedDevice(new TODevice());
		updateDeviceLov();
	}
	
	public void onClearApplication() {
		this.toServiceTokenGeneration.setSelectedApplication(new TOApplication());
		updateApplicationLov();
	}
	
	public void onClearUser() {
		this.toServiceTokenGeneration.setSelectedUser(new TOUser());
		updatePersonLov();
	}
	
	public void onTokenTypeChange() {
		onClearDevice();
		onClearApplication();
		onClearUser();
	}
	
	public boolean isVisibleDeviceLov() {
		return this.toServiceTokenGeneration.getType().equals(EnumTokenType.DEVICE_TOKEN);
	}
	
	public boolean isVisibleApplicationLov() {
		return this.toServiceTokenGeneration.getType().equals(EnumTokenType.APPLICATION_TOKEN);
	}
	
	public boolean isVisiblePersonLov() {
		return this.toServiceTokenGeneration.getType().equals(EnumTokenType.USER_AUTHENTICATION_TOKEN);
	}
	
	public Boolean getDisabledDialog() {
		return this.toServiceTokenGeneration.getId() != null;
	}
	
	public LovDeviceMBean getLovDeviceMBean() {
		return lovDeviceMBean;
	}

	public LovPersonMBean getLovPersonMBean() {
		return lovPersonMBean;
	}

	public LovApplicationMBean getLovApplicationMBean() {
		return lovApplicationMBean;
	}
	
	public TOServiceTokenGeneration getToServiceTokenGeneration() {
		return toServiceTokenGeneration;
	}

	public List<LabeledEnum<EnumTokenType>> getTokenTypes() {
		return tokenTypes;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "token_dialog";
	}

}

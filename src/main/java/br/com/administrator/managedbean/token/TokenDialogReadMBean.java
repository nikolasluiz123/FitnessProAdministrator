package br.com.administrator.managedbean.token;

import java.time.LocalDateTime;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.to.TOServiceToken;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.token.TokenDialogReadViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("tokenDialogReadMBean")
@ViewScoped
public class TokenDialogReadMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOServiceToken toServiceToken;

	@Inject
	private TokenDialogReadViewModel viewModel;

	public void init(TOServiceToken toServiceToken) {
		this.toServiceToken = toServiceToken;
	}

	public void onInvalidateToken() {
		try {
			viewModel.invalidateToken(this.toServiceToken.getId());
			this.toServiceToken = viewModel.getTokenService(this.toServiceToken.getId());
			
			FacesUtils.addSucccessMessage(getScreenBundleString("invalidate_token_success"), getMaintenanceBundleString("save_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler("tokenDialogReadMessages", exception, getScreenBundleString("token_invalidation_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler("tokenDialogReadMessages", exception, getScreenBundleString("token_invalidation_error_summary"));
		}
	}
	
	public boolean isDisabledDialog() {
		if (this.toServiceToken.getExpirationDate() == null) {
			return false;
		}
		
		return this.toServiceToken.getExpirationDate().isBefore(LocalDateTime.now());
	}
	
	public boolean isVisibleUser() {
		return this.toServiceToken.getUser() != null;
	}
	
	public boolean isVisibleDevice() {
		return this.toServiceToken.getDevice() != null;
	}
	
	public boolean isVisibleApplication() {
		return this.toServiceToken.getApplication() != null;
	}
	
	public TOServiceToken getToServiceToken() {
		return toServiceToken;
	}

	public void setToServiceToken(TOServiceToken toServiceToken) {
		this.toServiceToken = toServiceToken;
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.token.token_dialog_read";
	}

}

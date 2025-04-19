package br.com.administrator.managedbean.token;

import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.managedbean.token.lazymodel.LazyTokenDataModel;
import br.com.administrator.to.TOServiceToken;
import br.com.administrator.viewmodel.token.TokenSearchViewModel;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("tokenSearchMBean")
@ViewScoped
public class TokenSearchMBean extends AbstractPagingSearchMBean<TOServiceToken, LazyTokenDataModel> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TokenSearchViewModel viewModel;
	
	@Inject
	private LazyTokenDataModel lazyModel;
	
	@Inject
	private SecretKeyDialogMBean secretKeyDialogMBean;
	
	@Inject
	private TokenDialogReadMBean tokenDialogReadMBean;
	
	private List<LabeledEnum<EnumTokenType>> tokenTypes;
	
	@Override
	public void onInit() {
		this.tokenTypes = EnumTokenType.getEntries().stream().map(x -> getLabeledType(x)).toList();
	}
	
	private LabeledEnum<EnumTokenType> getLabeledType(EnumTokenType x) {
		return new LabeledEnum<EnumTokenType>(x, viewModel.getLabelTokenType(x));
	}

	public void onInvalidateAllTokens() {
		try {
			viewModel.invalidateAllTokens();
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("invalidate_token_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getScreenBundleString("invalidate_token_error_summary"));
		}
	}
	
	public void onGenerateNewSecretKey() {
		try {
			String key = viewModel.generateSecretKey();
			secretKeyDialogMBean.init(key);
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getScreenBundleString("secret_key_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getScreenBundleString("secret_key_error_summary"));
		}
	}
	
	public List<LabeledEnum<EnumTokenType>> getTokenTypes() {
		return tokenTypes;
	}

	@Override
	public void onRowSelect(SelectEvent<TOServiceToken> event) {
		this.tokenDialogReadMBean.init(event.getObject());
	}
	
	@Override
	public LazyTokenDataModel getLazyModel() {
		return lazyModel;
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.token.token_search";
	}

}

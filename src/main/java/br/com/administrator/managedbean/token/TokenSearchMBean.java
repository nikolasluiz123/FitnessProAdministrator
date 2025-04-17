package br.com.administrator.managedbean.token;

import java.util.List;

import br.com.administrator.managedbean.common.beans.AbstractSearchMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.viewmodel.token.TokenSearchViewModel;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("tokenSearchMBean")
@ViewScoped
public class TokenSearchMBean extends AbstractSearchMBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private TokenSearchViewModel viewModel;
	
	@Inject
	private LazyTokenDataModel lazyModel;
	
	@Inject
	private SecretKeyDialogMBean secretKeyDialogMBean;
	
	private List<LabeledEnum<EnumTokenType>> tokenTypes;
	
	@PostConstruct
	public void init() {
		this.lazyModel.setCallback(new DefaultLazyDataModelCallback());
		this.tokenTypes = EnumTokenType.getEntries().stream().map(x -> getLabeledType(x)).toList();
	}
	
	private LabeledEnum<EnumTokenType> getLabeledType(EnumTokenType x) {
		return new LabeledEnum<EnumTokenType>(x, viewModel.getLabelTokenType(x));
	}

	public void onInvalidateAllTokens() {
		try {
			viewModel.invalidateAllTokens();
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getBundleString("invalidate_token_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getBundleString("invalidate_token_error_summary"));
		}
	}
	
	public void onInvalidateToken(String tokenId) {
		try {
			viewModel.invalidateToken(tokenId);
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getBundleString("invalidate_token_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getBundleString("invalidate_token_error_summary"));
		}
	}
	
	public void onGenerateNewSecretKey() {
		try {
			String key = viewModel.generateSecretKey();
			secretKeyDialogMBean.init(key);
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getBundleString("secret_key_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getBundleString("secret_key_error_summary"));
		}
	}
	
	public void onNewToken() {
		
	}
	
	public LazyTokenDataModel getLazyModel() {
		return lazyModel;
	}
	
	public List<LabeledEnum<EnumTokenType>> getTokenTypes() {
		return tokenTypes;
	}

	public void onRequestReloadDatatable() {
		lazyModel.reloadPreservingPagingState();
	}

	@Override
	protected String getBundleFileName() {
		return "token_search";
	}

}

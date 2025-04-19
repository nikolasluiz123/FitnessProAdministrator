package br.com.administrator.viewmodel.token;

import java.io.Serializable;

import br.com.administrator.mappers.token.TokenMapper;
import br.com.administrator.service.webclient.TokenWebClient;
import br.com.administrator.to.TOServiceToken;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class TokenDialogReadViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final TokenWebClient webClient;
	private final TokenMapper mapper;

	@Inject
	public TokenDialogReadViewModel(TokenWebClient webClient, TokenMapper mapper) {
		this.webClient = webClient;
		this.mapper = mapper;
	}
	
	public void invalidateToken(String tokenId) throws Exception {
		webClient.invalidateToken(tokenId);
	}
	
	public TOServiceToken getTokenService(String tokenId) throws Exception {
		return mapper.getTOServiceTokenFrom(webClient.getTokenService(tokenId));
	}
	
}

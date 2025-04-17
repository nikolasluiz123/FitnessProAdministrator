package br.com.administrator.managedbean.lov;

import br.com.administrator.to.common.AbstractModelTO;

public interface ILovCallbacks<T extends AbstractModelTO> {

	void onItemSelected(T value); 
}

package br.com.administrator.managedbean.common.beans;

import br.com.administrator.managedbean.common.lazymodel.LazyDataModelCallback;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;

@SuppressWarnings("serial")
public abstract class AbstractPagingSearchMBean extends AbstractBaseMBean {

	public class DefaultLazyDataModelCallback implements LazyDataModelCallback {

		@Override
		public void onException(Exception exception) {
			exceptionHandler(exception, getBundleString("lazy_data_model_error_summary"));

			if (exception instanceof ExpiredTokenException || exception instanceof NotFoundTokenException) {
				showLoginDialog();
			}
		}
	}

}

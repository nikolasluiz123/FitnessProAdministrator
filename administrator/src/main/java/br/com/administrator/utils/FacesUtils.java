package br.com.administrator.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class FacesUtils {

	public static void addErrorMessage(String message, String summary) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, message);
		
		context.addMessage(null, facesMessage);
	}
}

package br.com.administrator.utils;

import java.util.ResourceBundle;

import org.primefaces.PrimeFaces;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

public class FacesUtils {

	public static void addErrorMessage(String clientId, String message, String summary) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, message);
		
        if (context.getPartialViewContext().isAjaxRequest()) {
            PrimeFaces.current().ajax().update("formMessages:messages");
        }
		
		context.addMessage(clientId, facesMessage);
	}
	
	public static void addErrorMessage(String message, String summary) {
		addErrorMessage(null, message, summary);
	}
	
	public static void addSucccessMessage(String clientId, String message, String summary) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, message);
		
		context.addMessage(clientId, facesMessage);
	}
	
	public static void addSucccessMessage(String message, String summary) {
		addSucccessMessage(null, message, summary);
	}
	
	public static void redirect(String path) throws Exception {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String url = externalContext.getRequestContextPath() + "/htdocs/" + path + ".jsf";
		
        externalContext.redirect(url);
	}
	
	public static ResourceBundle getResourceBundle(String path) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle(path, facesContext.getViewRoot().getLocale());
		
		return bundle;
	}
	
	public static void addRequestParam(String key, Object value) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.getRequestMap().put(key, value);
	}
	
	public static Object getRequestParamAndRemove(String key) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Object value = externalContext.getRequestMap().get(key);
		externalContext.getRequestMap().remove(key);
		
		return value;
	}
}

package br.com.administrator.managedbean.common.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("NoMaskConverter")
public class NoMaskConverter implements Converter<String> {

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return value.replaceAll("[^0-9]", "");
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }
}

package br.com.administrator.managedbean.common.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "PhoneConverter")
public class PhoneConverter implements Converter<String> {

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("\\D", ""); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() < 10) {
            return value;
        }
        if (value.length() == 11) {
            return String.format("(%s) %s-%s", value.substring(0, 2), value.substring(2, 7), value.substring(7));
        } else { 
            return String.format("(%s) %s-%s", value.substring(0, 2), value.substring(2, 6), value.substring(6));
        }
    }
}
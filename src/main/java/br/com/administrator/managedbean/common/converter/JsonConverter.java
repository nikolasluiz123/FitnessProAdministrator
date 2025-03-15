package br.com.administrator.managedbean.common.converter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("JsonConverter")
public class JsonConverter implements Converter<String> {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, String value) {
        if (value == null || !(value instanceof String)) {
            return "";
        }
        try {
            JsonElement jsonElement = JsonParser.parseString((String) value);
            return gson.toJson(jsonElement);
        } catch (Exception e) {
            return "JSON inválido!";
        }
    }
}

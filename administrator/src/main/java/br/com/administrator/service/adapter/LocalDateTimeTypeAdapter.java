package br.com.administrator.service.adapter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	@Override
	public JsonElement serialize(LocalDateTime localDateTime, Type type,
			JsonSerializationContext jsonSerializationContext) {
		return new JsonPrimitive(DATE_TIME_FORMATTER.format(localDateTime));
	}

	@Override
	public LocalDateTime deserialize(JsonElement jsonElement, Type type,
			JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return LocalDateTime.parse(jsonElement.getAsString(), DATE_TIME_FORMATTER);
	}

}

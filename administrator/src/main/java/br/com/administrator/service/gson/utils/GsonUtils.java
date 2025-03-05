package br.com.administrator.service.gson.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.administrator.service.gson.adapter.LocalDateTimeTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalDateTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalTimeTypeAdapter;

public class GsonUtils {

	public static Gson getDefaultGson() {
		return new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
		        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
		        .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
		        .create();
	}
}

package br.com.administrator.utils.gson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.google.gson.GsonBuilder;

import br.com.administrator.service.gson.adapter.LocalDateTimeTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalDateTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalTimeTypeAdapter;

public final class GSonUtils {

	public static GsonBuilder getDefaultGsonBuilder() {
		return new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, LocalDateTimeTypeAdapter.class)
				.registerTypeAdapter(LocalDate.class, LocalDateTypeAdapter.class)
				.registerTypeAdapter(LocalTime.class, LocalTimeTypeAdapter.class);
	}
}

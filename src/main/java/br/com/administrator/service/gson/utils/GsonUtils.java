package br.com.administrator.service.gson.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.administrator.service.gson.adapter.ApplicationDTOTypeAdapter;
import br.com.administrator.service.gson.adapter.DeviceDTOTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalDateTimeTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalDateTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalTimeTypeAdapter;
import br.com.administrator.service.gson.adapter.PersonDTOTypeAdapter;
import br.com.administrator.service.gson.adapter.SchedulerDTOTypeAdapter;
import br.com.administrator.service.gson.adapter.UserDTOTypeAdapter;
import br.com.administrator.service.gson.adapter.WorkoutDTOTypeAdapter;
import br.com.administrator.service.gson.adapter.WorkoutGroupDTOTypeAdapter;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IPersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IUserDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.interfaces.ISchedulerDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IApplicationDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IDeviceDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IWorkoutDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IWorkoutGroupDTO;

public class GsonUtils {

	public static Gson getDefaultGson() {
		return getDefaultGson(false);
	}
	
	public static Gson getDefaultGson(Boolean prettyPrint) {
		GsonBuilder builder = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
		        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
		        .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
		        .registerTypeAdapter(IApplicationDTO.class, new ApplicationDTOTypeAdapter())
		        .registerTypeAdapter(IDeviceDTO.class, new DeviceDTOTypeAdapter())
		        .registerTypeAdapter(IPersonDTO.class, new PersonDTOTypeAdapter())
		        .registerTypeAdapter(ISchedulerDTO.class, new SchedulerDTOTypeAdapter())
		        .registerTypeAdapter(IUserDTO.class, new UserDTOTypeAdapter())
		        .registerTypeAdapter(IWorkoutDTO.class, new WorkoutDTOTypeAdapter())
		        .registerTypeAdapter(IWorkoutGroupDTO.class, new WorkoutGroupDTOTypeAdapter());
		
		if (prettyPrint) {
			builder.setPrettyPrinting();
		}
		
		return builder.create();
	}
}

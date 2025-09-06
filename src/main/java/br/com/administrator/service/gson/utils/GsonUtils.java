package br.com.administrator.service.gson.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.administrator.service.gson.adapter.GenericInterfaceAdapterFactory;
import br.com.administrator.service.gson.adapter.LocalDateTimeTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalDateTypeAdapter;
import br.com.administrator.service.gson.adapter.LocalTimeTypeAdapter;
import br.com.administrator.service.gson.adapter.OffsetDateTimeTypeAdapter;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheClearConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.CacheEntryDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.interfaces.ICacheClearConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.interfaces.ICacheDTO;
import br.com.fitnesspro.shared.communication.dtos.cache.interfaces.ICacheEntryDTO;
import br.com.fitnesspro.shared.communication.dtos.general.AcademyDTO;
import br.com.fitnesspro.shared.communication.dtos.general.AuthenticationDTO;
import br.com.fitnesspro.shared.communication.dtos.general.FindPersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.PersonAcademyTimeDTO;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.ReportDTO;
import br.com.fitnesspro.shared.communication.dtos.general.SchedulerReportDTO;
import br.com.fitnesspro.shared.communication.dtos.general.UserDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IAcademyDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IAuthenticationDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IFindPersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IPersonAcademyTimeDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IPersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IReportDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.ISchedulerReportDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IUserDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogPackageDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.UpdatableExecutionLogInfosDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.UpdatableExecutionLogPackageInfosDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.interfaces.IExecutionLogDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.interfaces.IExecutionLogPackageDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.interfaces.IUpdatableExecutionLogInfosDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.interfaces.IUpdatableExecutionLogPackageInfosDTO;
import br.com.fitnesspro.shared.communication.dtos.notification.GlobalNotificationDTO;
import br.com.fitnesspro.shared.communication.dtos.notification.NotificationDTO;
import br.com.fitnesspro.shared.communication.dtos.notification.interfaces.IGlobalNotificationDTO;
import br.com.fitnesspro.shared.communication.dtos.notification.interfaces.INotificationDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduledtask.IScheduledTaskDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduledtask.ScheduledTaskDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.RecurrentSchedulerDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.SchedulerConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.SchedulerDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.interfaces.IRecurrentSchedulerDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.interfaces.ISchedulerConfigDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.interfaces.ISchedulerDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ServiceTokenGenerationDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IApplicationDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IDeviceDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IServiceTokenDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IServiceTokenGenerationDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.ExerciseDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.ExerciseExecutionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.ExercisePreDefinitionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.VideoDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.VideoExerciseDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.VideoExerciseExecutionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.VideoExercisePreDefinitionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.WorkoutDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.WorkoutGroupDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.WorkoutGroupPreDefinitionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IExerciseDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IExerciseExecutionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IExercisePreDefinitionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IVideoDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IVideoExerciseDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IVideoExerciseExecutionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IVideoExercisePreDefinitionDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IWorkoutDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IWorkoutGroupDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IWorkoutGroupPreDefinitionDTO;

public class GsonUtils {

    public static Gson getDefaultGson() {
        return getDefaultGson(false);
    }

    public static Gson getDefaultGson(Boolean prettyPrint) {
        GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
            .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeTypeAdapter())

            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(ICacheClearConfigDTO.class, CacheClearConfigDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(ICacheDTO.class, CacheDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(ICacheEntryDTO.class, CacheEntryDTO.class))

            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IAcademyDTO.class, AcademyDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IAuthenticationDTO.class, AuthenticationDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IFindPersonDTO.class, FindPersonDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IPersonAcademyTimeDTO.class, PersonAcademyTimeDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IPersonDTO.class, PersonDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IReportDTO.class, ReportDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(ISchedulerReportDTO.class, SchedulerReportDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IUserDTO.class, UserDTO.class))

            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IExecutionLogDTO.class, ExecutionLogDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IExecutionLogPackageDTO.class, ExecutionLogPackageDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IUpdatableExecutionLogInfosDTO.class, UpdatableExecutionLogInfosDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IUpdatableExecutionLogPackageInfosDTO.class, UpdatableExecutionLogPackageInfosDTO.class))

            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IGlobalNotificationDTO.class, GlobalNotificationDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(INotificationDTO.class, NotificationDTO.class))

            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IScheduledTaskDTO.class, ScheduledTaskDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IRecurrentSchedulerDTO.class, RecurrentSchedulerDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(ISchedulerConfigDTO.class, SchedulerConfigDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(ISchedulerDTO.class, SchedulerDTO.class))

            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IApplicationDTO.class, ApplicationDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IDeviceDTO.class, DeviceDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IServiceTokenDTO.class, ServiceTokenDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IServiceTokenGenerationDTO.class, ServiceTokenGenerationDTO.class))

            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IExerciseDTO.class, ExerciseDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IExerciseExecutionDTO.class, ExerciseExecutionDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IExercisePreDefinitionDTO.class, ExercisePreDefinitionDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IVideoDTO.class, VideoDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IVideoExerciseDTO.class, VideoExerciseDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IVideoExerciseExecutionDTO.class, VideoExerciseExecutionDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IVideoExercisePreDefinitionDTO.class, VideoExercisePreDefinitionDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IWorkoutDTO.class, WorkoutDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IWorkoutGroupDTO.class, WorkoutGroupDTO.class))
            .registerTypeAdapterFactory(new GenericInterfaceAdapterFactory<>(IWorkoutGroupPreDefinitionDTO.class, WorkoutGroupPreDefinitionDTO.class));

        if (prettyPrint) {
            builder.setPrettyPrinting();
        }

        return builder.create();
    }
}

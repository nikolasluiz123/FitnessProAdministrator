package br.com.administrator.service.gson.adapter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.workout.WorkoutDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IWorkoutDTO;

public class WorkoutDTOTypeAdapter extends TypeAdapter<IWorkoutDTO> {
    @Override
    public void write(JsonWriter out, IWorkoutDTO value) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        gson.toJson((WorkoutDTO) value, WorkoutDTO.class, out);
    }

    @Override
    public IWorkoutDTO read(JsonReader in) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        return gson.fromJson(in, WorkoutDTO.class);
    }
}
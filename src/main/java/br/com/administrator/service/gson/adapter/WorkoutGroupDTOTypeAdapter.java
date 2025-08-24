package br.com.administrator.service.gson.adapter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.workout.WorkoutGroupDTO;
import br.com.fitnesspro.shared.communication.dtos.workout.interfaces.IWorkoutGroupDTO;

public class WorkoutGroupDTOTypeAdapter extends TypeAdapter<IWorkoutGroupDTO> {
    @Override
    public void write(JsonWriter out, IWorkoutGroupDTO value) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        gson.toJson((WorkoutGroupDTO) value, WorkoutGroupDTO.class, out);
    }

    @Override
    public IWorkoutGroupDTO read(JsonReader in) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        return gson.fromJson(in, WorkoutGroupDTO.class);
    }
}
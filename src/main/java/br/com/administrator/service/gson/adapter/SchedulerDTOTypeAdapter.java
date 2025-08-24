package br.com.administrator.service.gson.adapter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.scheduler.SchedulerDTO;
import br.com.fitnesspro.shared.communication.dtos.scheduler.interfaces.ISchedulerDTO;

public class SchedulerDTOTypeAdapter extends TypeAdapter<ISchedulerDTO> {
    @Override
    public void write(JsonWriter out, ISchedulerDTO value) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        gson.toJson((SchedulerDTO) value, SchedulerDTO.class, out);
    }

    @Override
    public ISchedulerDTO read(JsonReader in) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        return gson.fromJson(in, SchedulerDTO.class);
    }
}
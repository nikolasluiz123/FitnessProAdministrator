package br.com.administrator.service.gson.adapter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.ApplicationDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IApplicationDTO;
public class ApplicationDTOTypeAdapter extends TypeAdapter<IApplicationDTO> {
    @Override
    public void write(JsonWriter out, IApplicationDTO value) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        gson.toJson((ApplicationDTO) value, ApplicationDTO.class, out);
    }

    @Override
    public IApplicationDTO read(JsonReader in) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        return gson.fromJson(in, ApplicationDTO.class);
    }
}
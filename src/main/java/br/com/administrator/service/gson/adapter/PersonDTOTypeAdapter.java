package br.com.administrator.service.gson.adapter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.general.PersonDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IPersonDTO;

public class PersonDTOTypeAdapter extends TypeAdapter<IPersonDTO> {
    @Override
    public void write(JsonWriter out, IPersonDTO value) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        gson.toJson((PersonDTO) value, PersonDTO.class, out);
    }

    @Override
    public IPersonDTO read(JsonReader in) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        return gson.fromJson(in, PersonDTO.class);
    }
}
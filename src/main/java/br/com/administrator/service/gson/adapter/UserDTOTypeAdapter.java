package br.com.administrator.service.gson.adapter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.general.UserDTO;
import br.com.fitnesspro.shared.communication.dtos.general.interfaces.IUserDTO;

public class UserDTOTypeAdapter extends TypeAdapter<IUserDTO> {
    @Override
    public void write(JsonWriter out, IUserDTO value) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        gson.toJson((UserDTO) value, UserDTO.class, out);
    }

    @Override
    public IUserDTO read(JsonReader in) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        return gson.fromJson(in, UserDTO.class);
    }
}
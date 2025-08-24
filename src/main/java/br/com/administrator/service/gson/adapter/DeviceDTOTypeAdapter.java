package br.com.administrator.service.gson.adapter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.administrator.service.gson.utils.GsonUtils;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.interfaces.IDeviceDTO;

public class DeviceDTOTypeAdapter extends TypeAdapter<IDeviceDTO> {
    @Override
    public void write(JsonWriter out, IDeviceDTO value) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        gson.toJson((DeviceDTO) value, DeviceDTO.class, out);
    }

    @Override
    public IDeviceDTO read(JsonReader in) throws IOException {
        Gson gson = GsonUtils.getDefaultGson(false);
        return gson.fromJson(in, DeviceDTO.class);
    }
}
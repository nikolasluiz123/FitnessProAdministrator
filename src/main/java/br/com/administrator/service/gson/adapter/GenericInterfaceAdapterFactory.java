package br.com.administrator.service.gson.adapter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GenericInterfaceAdapterFactory<I, T extends I> implements TypeAdapterFactory {

    private final Class<I> interfaceType;
    private final Class<T> implementationType;

    public GenericInterfaceAdapterFactory(Class<I> interfaceType, Class<T> implementationType) {
        this.interfaceType = interfaceType;
        this.implementationType = implementationType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (type.getRawType() == interfaceType) {
            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, TypeToken.get(implementationType));

            return (TypeAdapter<R>) new TypeAdapter<I>() {
                @Override
                public void write(JsonWriter out, I value) throws IOException {
                    delegate.write(out, (T) value);
                }

                @Override
                public I read(JsonReader in) throws IOException {
                    return delegate.read(in);
                }
            };
        }

        return null;
    }
}

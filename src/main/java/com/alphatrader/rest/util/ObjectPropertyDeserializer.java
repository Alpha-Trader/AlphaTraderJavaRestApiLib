package com.alphatrader.rest.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.beans.property.*;

import java.io.IOException;

/**
 * Deserializes any object property.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ObjectPropertyDeserializer<T> extends TypeAdapter<Property<T>> {
    private final TypeAdapter<T> delegate;

    public ObjectPropertyDeserializer(TypeAdapter<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(JsonWriter jsonWriter, Property<T> objectProperty) throws IOException {
        delegate.write(jsonWriter, objectProperty.getValue());
    }

    @Override
    public Property<T> read(JsonReader jsonReader) throws IOException {
        ObjectProperty<T> myReturn;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            myReturn = new SimpleObjectProperty<>();
        }
        else {
            myReturn = new SimpleObjectProperty<>(delegate.read(jsonReader));
        }
        return myReturn;
    }
}

package com.alphatrader.rest.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

/**
 * Deserializes a simple boolean property.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public final class BooleanPropertyDeserializer extends TypeAdapter<BooleanProperty> {
    @Override
    public void write(JsonWriter jsonWriter, BooleanProperty doubleProperty) throws IOException {
        jsonWriter.value(doubleProperty.getValue());
    }

    @Contract("_ -> !null")
    @Override
    public BooleanProperty read(JsonReader jsonReader) throws IOException {
        BooleanProperty myReturn;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            myReturn = new SimpleBooleanProperty();
        }
        else {
            myReturn = new SimpleBooleanProperty(jsonReader.nextBoolean());
        }
        return myReturn;
    }
}

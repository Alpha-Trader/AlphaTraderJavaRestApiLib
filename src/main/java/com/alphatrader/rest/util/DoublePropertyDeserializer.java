package com.alphatrader.rest.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

/**
 * Deserializes a double property.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
final class DoublePropertyDeserializer extends TypeAdapter<DoubleProperty> {
    @Override
    public void write(JsonWriter jsonWriter, DoubleProperty doubleProperty) throws IOException {
        jsonWriter.value(doubleProperty.getValue());
    }

    @Contract("_ -> !null")
    @Override
    public DoubleProperty read(JsonReader jsonReader) throws IOException {
        DoubleProperty myReturn;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            myReturn = new SimpleDoubleProperty();
        }
        else {
            myReturn = new SimpleDoubleProperty(jsonReader.nextDouble());
        }
        return myReturn;
    }
}

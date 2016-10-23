package com.alphatrader.rest.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;

/**
 * Deserializes a string property.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class StringPropertyDeserializer extends TypeAdapter<StringProperty> {
    @Override
    public void write(JsonWriter jsonWriter, StringProperty stringProperty) throws IOException {
        jsonWriter.value(stringProperty.getValue());
    }

    @Override
    public StringProperty read(JsonReader jsonReader) throws IOException {
        StringProperty myReturn;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            myReturn = new SimpleStringProperty();
        }
        else {
            myReturn = new SimpleStringProperty(jsonReader.nextString());
        }
        return myReturn;
    }
}

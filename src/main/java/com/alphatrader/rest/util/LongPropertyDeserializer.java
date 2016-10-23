package com.alphatrader.rest.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import java.io.IOException;

/**
 * Deserializes a simple double property.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class LongPropertyDeserializer extends TypeAdapter<LongProperty> {
    @Override
    public void write(JsonWriter jsonWriter, LongProperty longProperty) throws IOException {
        jsonWriter.value(longProperty.getValue());
    }

    @Override
    public LongProperty read(JsonReader jsonReader) throws IOException {
        LongProperty myReturn;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            myReturn = new SimpleLongProperty();
        }
        else {
            myReturn = new SimpleLongProperty(jsonReader.nextLong());
        }
        return myReturn;
    }
}

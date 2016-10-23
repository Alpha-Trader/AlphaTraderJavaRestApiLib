package com.alphatrader.rest.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

/**
 * Wrapper class for our personalized version of the gson builder.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public final class PropertyGson {
    /**
     * @return the customized gson builder for this class.
     */
    @NotNull
    public Gson create() {
        return new GsonBuilder()
            .registerTypeAdapter(BooleanProperty.class, new BooleanPropertyDeserializer())
            .registerTypeAdapter(DoubleProperty.class, new DoublePropertyDeserializer())
            .registerTypeAdapter(LongProperty.class, new LongPropertyDeserializer())
            .registerTypeAdapter(StringProperty.class, new StringPropertyDeserializer())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer())
            .create();
    }
}

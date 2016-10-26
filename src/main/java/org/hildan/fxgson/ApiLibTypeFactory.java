package org.hildan.fxgson;

import com.alphatrader.rest.util.*;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import org.hildan.fxgson.adapters.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.hildan.fxgson.TypeHelper.withRawType;

/**
 * Registers our own property adapters with null value handling. Currently a very dirty trick, hope this
 * will be fixed once the FxGson guy fixes my issue.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ApiLibTypeFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> clazz = type.getRawType();

        // this factory only handles JavaFX property types
        if (!Property.class.isAssignableFrom(clazz)) {
            return null;
        }

        // simple property types

        if (BooleanProperty.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new BooleanPropertyDeserializer();
        }
        if (IntegerProperty.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new IntegerPropertyTypeAdapter();
        }
        if (LongProperty.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new LongPropertyDeserializer();
        }
        if (FloatProperty.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new FloatPropertyTypeAdapter();
        }
        if (DoubleProperty.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new DoublePropertyDeserializer();
        }
        if (StringProperty.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new StringPropertyDeserializer();
        }

        // collection property types

        if (ListProperty.class.isAssignableFrom(clazz)) {
            TypeAdapter<?> delegate = gson.getAdapter(withRawType(type, ObservableList.class));
            return new ListPropertyTypeAdapter(delegate);
        }
        if (SetProperty.class.isAssignableFrom(clazz)) {
            TypeAdapter<?> delegate = gson.getAdapter(withRawType(type, ObservableSet.class));
            return new SetPropertyTypeAdapter(delegate);
        }
        if (MapProperty.class.isAssignableFrom(clazz)) {
            TypeAdapter<?> delegate = gson.getAdapter(withRawType(type, ObservableMap.class));
            return new MapPropertyTypeAdapter(delegate);
        }

        // generic Property<?> type

        Type[] typeParams = ((ParameterizedType) type.getType()).getActualTypeArguments();
        Type param = typeParams[0];
        // null factory skipPast because the nested type argument might also be a Property
        TypeAdapter<?> delegate = gson.getAdapter(TypeToken.get(param));
        return (TypeAdapter<T>) new ObjectPropertyDeserializer<>(delegate);
    }
}


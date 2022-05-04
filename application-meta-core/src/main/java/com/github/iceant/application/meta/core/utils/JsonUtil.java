package com.github.iceant.application.meta.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.github.iceant.application.meta.core.*;
import com.github.iceant.application.meta.core.jackson.*;

import java.util.List;

public class JsonUtil {
    public static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.findAndRegisterModules();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(DataTypeCollection.class, DataTypeCollectionDeserializer.INSTANCE);
        module.addDeserializer(ITypeValue.class, TypeValueDeserializer.INSTANCE);
        module.addDeserializer(DataType.class, DataTypeDeserializer.INSTANCE);
        module.addDeserializer(DataShapeCollection.class, DataShapeCollectionDeserializer.INSTANCE);
        module.addDeserializer(FieldShapeCollection.class, FieldShapeCollectionDeserializer.INSTANCE);
        module.addDeserializer(AspectCollection.class, AspectCollectionDeserializer.INSTANCE);
        objectMapper.registerModule(module);
        return objectMapper;
    }

    public static <T> T fromJson(String json, Class<T> type){
        try {
            return getObjectMapper().readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object object){
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJsonToList(String json , Class<T> type){
        try {
            ObjectMapper objectMapper = getObjectMapper();
            CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);

            return objectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T[] fromJsonToArray(String from, Class<T[]> arrayType) {
        try {
            return getObjectMapper().readValue(from, arrayType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

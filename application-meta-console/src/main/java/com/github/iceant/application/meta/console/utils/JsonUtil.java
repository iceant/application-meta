package com.github.iceant.application.meta.console.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class JsonUtil {
    public static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        return objectMapper;
    }

    public static String toJson(Object object){
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type){
        try {
            return getObjectMapper().readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> type){
        ObjectMapper objectMapper = getObjectMapper();
        try {
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + type.getName() + ";");
            return Arrays.asList(objectMapper.readValue(json, arrayClass)); // more fast
//            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

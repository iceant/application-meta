package com.github.iceant.application.meta.core.utils;

public class ClassUtil {
    public static <T> Class<T> forName(String name){
        try {
            return (Class<T>) Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

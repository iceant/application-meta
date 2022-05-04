package com.github.iceant.application.meta.core;

public interface IConverter<F, T>{
    T convert(F from);

    default T convert(F from, T defaultValue){
        if(from==null) return defaultValue;
        return convert(from);
    }
}

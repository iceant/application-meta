package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;
import com.github.iceant.application.meta.core.utils.JsonUtil;

public class StringToArray<T> implements IConverter<String, T[]> {
    final Class<T[]> arrayType;

    public StringToArray(Class<T[]> arrayType) {
        this.arrayType = arrayType;
    }


    @Override
    public T[] convert(String from) {
        return JsonUtil.fromJsonToArray(from, arrayType);
    }
}

package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;
import com.github.iceant.application.meta.core.utils.JsonUtil;

public class StringToObject<T> implements IConverter<String, T> {
    final Class<T> type;

    public StringToObject(Class<T> type) {
        this.type = type;
    }

    public static <T> StringToObject<T> of(Class<T> type){
        return new StringToObject<>(type);
    }

    @Override
    public T convert(String from) {
        return JsonUtil.fromJson(from, type);
    }
}

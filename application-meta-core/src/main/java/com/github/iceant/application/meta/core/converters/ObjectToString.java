package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;
import com.github.iceant.application.meta.core.utils.JsonUtil;

public class ObjectToString<T> implements IConverter<T, String> {
    @Override
    public String convert(T from) {
        return JsonUtil.toJson(from);
    }
}

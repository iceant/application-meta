package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;
import com.github.iceant.application.meta.core.utils.JsonUtil;

import java.util.Arrays;

public class ArrayToString<T> implements IConverter<T[], String> {
    @Override
    public String convert(T[] from) {
        return JsonUtil.toJson(Arrays.asList(from));
    }
}

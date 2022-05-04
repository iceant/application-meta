package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;

public class StringToInteger implements IConverter<String, Integer> {
    public static final StringToInteger INSTANCE = new StringToInteger();
    
    @Override
    public Integer convert(String from) {
        return Integer.valueOf(from);
    }
}

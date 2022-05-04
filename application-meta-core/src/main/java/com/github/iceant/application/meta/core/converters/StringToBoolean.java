package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;

public class StringToBoolean implements IConverter<String, Boolean> {
    @Override
    public Boolean convert(String from) {
        return Boolean.valueOf(from);
    }
}

package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;

import java.math.BigDecimal;

public class StringToBigDecimal implements IConverter<String, BigDecimal> {
    @Override
    public BigDecimal convert(String from) {
        return new BigDecimal(from);
    }
}

package com.github.iceant.application.meta.core.converters;

import com.github.iceant.application.meta.core.IConverter;

import java.math.BigInteger;

public class StringToBigInteger implements IConverter<String, BigInteger> {
    @Override
    public BigInteger convert(String from) {
        return new BigInteger(from);
    }
}

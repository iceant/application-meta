package com.github.iceant.application.meta.core.converters;

import at.favre.lib.bytes.Bytes;
import com.github.iceant.application.meta.core.IConverter;

public class BlobToString implements IConverter<byte[], String> {
    @Override
    public String convert(byte[] from) {
        return Bytes.from(from).encodeHex();
    }
}

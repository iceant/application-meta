package com.github.iceant.application.meta.core.converters;

import at.favre.lib.bytes.Bytes;
import com.github.iceant.application.meta.core.IConverter;


public class StringToBlob implements IConverter<String, byte[]> {
    @Override
    public byte[] convert(String from) {
        return Bytes.parseHex(from).array();
    }
}

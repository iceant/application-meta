package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ByteTypeValue extends BasicTypeValue<ByteTypeValue, Byte> {
    @Override
    public String getType() {
        return "BYTE";
    }
}

package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class ArrayTypeValue<T> extends BasicTypeValue<ArrayTypeValue, T[]> {

    final String elementTypeName;

    public ArrayTypeValue(String elementTypeName) {
        this.elementTypeName = elementTypeName;
    }

    @Override
    public String getType() {
        return "ARRAY";
    }
}

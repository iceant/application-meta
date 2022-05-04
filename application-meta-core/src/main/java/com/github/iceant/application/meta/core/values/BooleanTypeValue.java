package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class BooleanTypeValue extends BasicTypeValue<BooleanTypeValue, Boolean> {
    @Override
    public String getType() {
        return "BOOLEAN";
    }
}

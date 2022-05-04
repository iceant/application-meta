package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import lombok.Data;
import lombok.ToString;

@ToString(callSuper = true)
@Data
public class StringTypeValue extends BasicTypeValue<StringTypeValue, String> {
    @Override
    public String getType() {
        return "STRING";
    }
}

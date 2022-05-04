package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ToString(callSuper = true)
@Data
public class BigDecimalTypeValue extends BasicTypeValue<BigDecimalTypeValue, BigDecimal> {
    @Override
    public String getType() {
        return "DECIMAL";
    }
}

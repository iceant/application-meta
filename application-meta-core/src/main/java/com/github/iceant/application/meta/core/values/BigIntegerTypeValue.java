package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString(callSuper = true)
public class BigIntegerTypeValue extends BasicTypeValue<BigIntegerTypeValue, BigInteger> {
    @Override
    public String getType() {
        return "INTEGER";
    }
}

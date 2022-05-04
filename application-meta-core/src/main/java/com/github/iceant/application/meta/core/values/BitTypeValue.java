package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class BitTypeValue extends BasicTypeValue<BitTypeValue, Byte> {
    final Integer offset; // byte 的第几个位置

    public BitTypeValue(Integer offset){
        this.offset = offset;
    }

    @Override
    public String getType() {
        return "BIT";
    }

}

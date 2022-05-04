package com.github.iceant.application.meta.core;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DataType extends NamedObject<DataType> {
    PrimitiveDataType valueType = PrimitiveDataType.BYTE;
    Long length = 1L;
    Integer ordinal = 0;

    public DataType setLength(Long length){
        this.length = length;
        return this;
    }

    public DataType setLength(Integer length){
        this.length = Long.valueOf(length);
        return this;
    }

    public DataType setLength(FieldLength fieldLength){
        this.length = fieldLength.value();
        return this;
    }
}

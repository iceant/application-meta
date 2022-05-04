package com.github.iceant.application.meta.core;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class FieldShape extends NamedObject<FieldShape>{
    Boolean isPrimaryKey;
    Boolean isNullable;
    Boolean isForeignKey;           // 是否引用字段
    String foreignDataShapeName;    // 引用的目标数据结构
    String foreignFieldShapeName;   // 引用的目标字段
    Integer fieldLength = 1;
    Integer fieldMaxLength = 1;
    PrimitiveDataType valueType;
    String defaultValue;
    String minValue;
    String maxValue;
    EnumValue  enumValue;
    RangeValue rangeValue;
    BigDecimal valueOffset = BigDecimal.ZERO;
    BigDecimal valueResolution = BigDecimal.ONE;
    String unit;
    Integer ordinal = 0;
    AspectCollection aspects = new AspectCollection();

    public FieldShape setDataType(String dataTypeName){
        aspects.setString(Aspects.ASPECTS_DATA_TYPE, dataTypeName);
        return this;
    }

    public String getDataType(){
        if(aspects.has(Aspects.ASPECTS_DATA_TYPE)) {
            return aspects.getString(Aspects.ASPECTS_DATA_TYPE);
        }else{
            return null;
        }
    }


    public FieldShape setStringAspect(String name, String value){
        aspects.setString(name, value);
        return this;
    }

    public FieldShape setIntegerAspect(String name, Integer value) {
        aspects.setInteger(name, value);
        return this;
    }

    @JsonSetter
    public FieldShape setFieldLength(Integer fieldLength){
        this.fieldLength = fieldLength;
        this.fieldMaxLength = fieldLength;
        return this;
    }

    public FieldShape setFieldLength(FieldLength fieldLength){
        setFieldLength(fieldLength.value().intValue());
        return this;
    }

    public <T> FieldShape setArrayAspect(String name, String elementType, T  ... values) {
        aspects.setArray(name, elementType, values);
        return this;
    }
}

package com.github.iceant.application.meta.core;

import com.github.iceant.application.meta.core.values.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

public class AspectCollection extends HashMap<String, ITypeValue> {

    public Boolean has(String name){
        return containsKey(name);
    }

    public AspectCollection setString(String name, String value){
        put(name, new StringTypeValue().setValue(value));
        return this;
    }

    public AspectCollection setByte(String name, Byte value){
        put(name, new ByteTypeValue().setValue(value));
        return this;
    }

    public AspectCollection setInteger(String name, BigInteger value){
        put(name, new BigIntegerTypeValue().setValue(value));
        return this;
    }

    public AspectCollection setInteger(String name, Long value){
        return setInteger(name, BigInteger.valueOf(value));
    }

    public AspectCollection setInteger(String name, Integer value){
        return setInteger(name, BigInteger.valueOf(value));
    }

    public AspectCollection setDecimal(String name, BigDecimal value){
        put(name, new BigDecimalTypeValue().setValue(value));
        return this;
    }

    public AspectCollection setDecimal(String name, Double value){
        return setDecimal(name, BigDecimal.valueOf(value));
    }

    public AspectCollection setDecimal(String name, Float value){
        return setDecimal(name, BigDecimal.valueOf(value));
    }

    public AspectCollection setBooelan(String name, Boolean value){
        put(name, new BooleanTypeValue().setValue(value));
        return this;
    }

    public AspectCollection setDataShape(String name, DataShape dataShape){
        put(name, new DataShapeTypeValue(dataShape.getName()).setValue(dataShape));
        return this;
    }

    public <T> AspectCollection setArray(String name, String elementType, T[] value){
        put(name, new ArrayTypeValue<T>(elementType).setValue(value));
        return this;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    public <T extends ITypeValue> T getTypeValue(String name){
        return (T) get(name);
    }

    public String getString(String name){
        StringTypeValue stringTypeValue = getTypeValue(name);
        return stringTypeValue.getValue();
    }

    public Boolean getBoolean(String name){
        BooleanTypeValue booleanTypeValue = getTypeValue(name);
        return booleanTypeValue.getValue();
    }

    public BigInteger getInteger(String name){
        BigIntegerTypeValue bigIntegerTypeValue = getTypeValue(name);
        return bigIntegerTypeValue.getValue();
    }

    public BigDecimal getDecimal(String name){
        BigDecimalTypeValue bigDecimalTypeValue = getTypeValue(name);
        return bigDecimalTypeValue.getValue();
    }

    public DataShape getDataShape(String name){
        DataShapeTypeValue dataShapeTypeValue = getTypeValue(name);
        return dataShapeTypeValue.getValue();
    }

    public <T> T[] getArray(String name){
        ArrayTypeValue<T> arrayTypeValue = getTypeValue(name);
        return arrayTypeValue.getValue();
    }

}

package com.github.iceant.application.meta.core;

import com.github.iceant.application.meta.core.converters.StringToInteger;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RangeValue<T> {
    Boolean includeStart;
    Boolean includeEnd;
    T start;
    T end;

    public static RangeValue<Integer> ofInteger(String value){
        return fromString(value, StringToInteger.INSTANCE);
    }

    public static <T> RangeValue<T> fromString(String value, IConverter<String, T> converter){
        RangeValue<T> rangeValue = new RangeValue<>();

        if(value.startsWith("[")){
            rangeValue.includeStart = true;
        }else{
            rangeValue.includeStart = false;
        }

        if(value.endsWith("]")){
            rangeValue.includeEnd = true;
        }else{
            rangeValue.includeEnd = false;
        }

        String[] values = value.split("[,~-]");
        String startString = values[0].trim();
        String endString = values[1].trim();

        rangeValue.start = converter.convert((startString.startsWith("[")||startString.startsWith("("))?startString.substring(1):startString);
        rangeValue.end = converter.convert((endString.endsWith("]")||endString.endsWith(")"))?endString.substring(0, endString.length()-1):endString);
        return rangeValue;
    }
}

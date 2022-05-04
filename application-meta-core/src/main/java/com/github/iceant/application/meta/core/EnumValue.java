package com.github.iceant.application.meta.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@Accessors(chain = true)
public class EnumValue<T> extends ArrayList<T> {

    public static <T> EnumValue<T> of(T ... values) {
        EnumValue<T> enumValue = new EnumValue<>();
        enumValue.addAll(Arrays.asList(values));
        return enumValue;
    }
}

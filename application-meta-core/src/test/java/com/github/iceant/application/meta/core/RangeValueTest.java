package com.github.iceant.application.meta.core;

import com.github.iceant.application.meta.core.converters.StringToInteger;


class RangeValueTest {
    public static void main(String[] args) {
        RangeValue rangeValue = RangeValue.fromString("(0, 65535]", StringToInteger.INSTANCE);
        System.out.println(rangeValue);
    }
}
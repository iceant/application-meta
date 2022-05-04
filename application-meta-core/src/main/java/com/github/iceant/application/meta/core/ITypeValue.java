package com.github.iceant.application.meta.core;

public interface ITypeValue<T extends ITypeValue, V> {
    String getType();

    V getValue();

    T setValue(V value);
}

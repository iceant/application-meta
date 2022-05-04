package com.github.iceant.application.meta.core;

public class BasicTypeValue<T extends ITypeValue, V> implements ITypeValue<T, V>{
    String type;
    V value;

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public T setValue(V value) {
        this.value = value;
        return (T) this;
    }

    public String getType(){
        return type;
    }

    public T setType(String type){
        this.type = type;
        return (T) this;
    }
}

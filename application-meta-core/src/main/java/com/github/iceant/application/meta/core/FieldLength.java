package com.github.iceant.application.meta.core;

public enum FieldLength {
    UNKNOWN(-1),
    ZERO(0),
    ONE(1),
    TWO(2);

    private long length;

    FieldLength(long length){
        this.length = length;
    }

    public Long value(){
        return length;
    }
}

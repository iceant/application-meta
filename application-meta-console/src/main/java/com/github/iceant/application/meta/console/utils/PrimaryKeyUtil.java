package com.github.iceant.application.meta.console.utils;

public class PrimaryKeyUtil {
    static Snowflake snowflake = new Snowflake();

    public static Long nextId(){
        return snowflake.nextId();
    }
}

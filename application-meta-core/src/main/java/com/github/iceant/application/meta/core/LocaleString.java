package com.github.iceant.application.meta.core;

import java.util.HashMap;
import java.util.Locale;

public class LocaleString extends HashMap<Locale, String> {
    public String get(){
        return get(Locale.getDefault());
    }

    public LocaleString set(String value){
        put(Locale.getDefault(), value);
        return this;
    }
}

package com.github.iceant.application.meta.console.domain;

import com.github.iceant.application.meta.console.utils.JsonUtil;

import java.util.HashMap;
import java.util.Locale;

public class LocaleString extends HashMap<Locale, String> {

    public static LocaleString fromString(String json){
        return JsonUtil.fromJson(json, LocaleString.class);
    }

    public String toString(){
        return JsonUtil.toJson(this);
    }
}

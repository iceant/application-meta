package com.github.iceant.application.meta.console.domain;

import com.github.iceant.application.meta.console.utils.JsonUtil;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NamedValue<T> {
    String name;
    T value;

    public static <T> NamedValue<T> fromString(String json){
        return JsonUtil.fromJson(json, NamedValue.class);
    }

    public String toString(){
        return JsonUtil.toJson(this);
    }
}

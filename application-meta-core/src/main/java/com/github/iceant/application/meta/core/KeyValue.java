package com.github.iceant.application.meta.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class KeyValue<K, V> {
    K key;
    V value;

    public static <K, V> KeyValue<K, V> of(K key, V value) {
        return new KeyValue<K, V>().setKey(key).setValue(value);
    }

    public static <K, V> KeyValue<K, V> of(Map<String, Object> map){
        return new KeyValue<K, V>().setKey((K) map.get("key")).setValue((V)map.get("value"));
    }
}

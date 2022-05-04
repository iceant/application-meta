package com.github.iceant.application.meta.core;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Locale;

@Data
@Accessors(chain = true)
public class NamedObject<T extends NamedObject> {
    String name;
    LocaleString userFriendlyName = new LocaleString();
    LocaleString description = new LocaleString();

    public T setName(String name){
        this.name = name;
        return (T) this;
    }

    @JsonSetter
    public T setUserFriendlyName(LocaleString userFriendlyName){
        this.userFriendlyName = userFriendlyName;
        return (T) this;
    }

    @JsonSetter
    public T setDescription(LocaleString description){
        this.description = description;
        return (T) this;
    }

    public T setUserFriendlyName(Locale locale, String name){
        userFriendlyName.put(locale, name);
        return (T) this;
    }

    public T setUserFriendlyName(String name){
        userFriendlyName.put(Locale.getDefault(), name);
        return (T) this;
    }

    public T setDescription(Locale locale, String name){
        description.put(locale, name);
        return (T) this;
    }

    public T setDescription(String name){
        description.put(Locale.getDefault(), name);
        return (T) this;
    }
}

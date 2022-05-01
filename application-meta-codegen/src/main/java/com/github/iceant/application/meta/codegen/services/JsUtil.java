package com.github.iceant.application.meta.codegen.services;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Map;

public class JsUtil {

    public static interface CustomizeFunction{
        void customize(Context context, Scriptable scope);
    }

    public static Object javaToJs(Object object, Scriptable scope){
        return Context.javaToJS(object, scope);
    }

    public static void putIntoScope(Object javaObject, String name, Scriptable scope){
        Object jsObject = Context.javaToJS(javaObject, scope);
        scope.put(name, scope, jsObject);
    }

    public static <T> T invokeInClasspath(String scriptInClasspath, String functionName, Object[] args, Class<T> resultType, CustomizeFunction customizeFunction){
        Context context = Context.enter();
        Reader reader=null;
        try{
            context.setOptimizationLevel(9);
            context.setLanguageVersion(Context.VERSION_ES6);

            Scriptable scope = context.initStandardObjects();

            customizeFunction.customize(context, scope);

            URL url = ResourceUtils.getURL(scriptInClasspath);
            InputStream inputStream = url.openStream();
            reader = new InputStreamReader(inputStream);
            context.evaluateReader(scope, reader, null, 1, null);
            Object jsFnObj = scope.get(functionName, scope);
            if(jsFnObj instanceof Function){
                Function jsFn = (Function) jsFnObj;
                Object result = jsFn.call(context, scope, scope/*this*/, args);
                if(resultType!=null) {
                    return (T) Context.jsToJava(result, resultType);
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            context.close();
        }
    }

    public static <T> T runInClasspath(String resourceInClasspath, Map<String, Object> args, Class<T> type, CustomizeFunction customizeScopeFn){
        Context context = Context.enter();
        Reader reader=null;
        try{
            context.setOptimizationLevel(9);
            context.setLanguageVersion(Context.VERSION_ES6);

            Scriptable scope = context.initStandardObjects();
            for(String key:args.keySet()){
                Object value = args.get(key);
                Object jsValue = Context.javaToJS(value, scope);
                scope.put(key, scope, jsValue);
            }

            Object jsArgs = Context.javaToJS(args, scope);
            scope.put("$args", scope, jsArgs);

            if(customizeScopeFn!=null) {
                customizeScopeFn.customize(context, scope);
            }
            URL url = ResourceUtils.getURL(resourceInClasspath);
            InputStream inputStream = url.openStream();
            reader = new InputStreamReader(inputStream);
            Object result = context.evaluateReader(scope, reader, null, 1, null);
            if(type!=null && Void.class!=type) {
                return (T) Context.jsToJava(result, type);
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            context.close();
        }
    }
}

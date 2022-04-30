package com.github.iceant.application.meta.codegen.services;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Data
@Accessors(chain = true)
public class CustomizeBeetlTemplateEngine extends BeetlTemplateEngine {
    String generatorName;

//    @Override
//    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
////        String entityName = tableInfo.getEntityName();
////        String otherPath = getPathInfo(OutputFile.other);
////        customFile.forEach((key, value) -> {
////            String fileName = String.format((otherPath + File.separator + entityName + File.separator + "%s"), key);
////            outputFile(new File(fileName), objectMap, value, getConfigBuilder().getInjectionConfig().isFileOverride());
////        });
//        String entityName = tableInfo.getEntityName();
//        String otherPath = getPathInfo(OutputFile.other);
//        log.info("Other Path:{}", otherPath);
//        String parentPath = getConfigBuilder().getPackageConfig().getParent();
//        log.info("Parent Path:{}", parentPath);
//        String outputDir = getConfigBuilder().getGlobalConfig().getOutputDir();
//        Map<String, Object> customMap = getConfigBuilder().getInjectionConfig().getCustomMap();
//        Map<String, Object> mergedObjectMap = new HashMap<>(objectMap);
//        mergedObjectMap.putAll(customMap);
//
//        customFile.forEach((key, value) -> {
//            Object path = customMap.get(key+".path");
//            String fileName = String.format((outputDir+File.separator+"%s" + File.separator + path + File.separator + entityName+ "%s"), parentPath.replace('.', File.separatorChar), key);
//            log.info("Output file: {}", fileName);
//            outputFile(new File(fileName), mergedObjectMap, value, getConfigBuilder().getInjectionConfig().isFileOverride());
//        });
//
//    }

    public String makePackage(ConfigBuilder config, String packageName){
        PackageConfig packageConfig = config.getPackageConfig();
        return packageConfig.getParent()+"."+packageName;
    }

    public String makeOutputPath(ConfigBuilder config, String packageName, String fileName){
        String pkgName = makePackage(config, packageName);
        String filePath = Paths.get(config.getGlobalConfig().getOutputDir(), pkgName.replace('.', '/'), fileName).toString();
        return filePath;
    }

    public String makePath(ConfigBuilder config, String subPath){
        return Paths.get(config.getGlobalConfig().getOutputDir(), subPath).toString();
    }

    private Map<String, Object> makeSimpleObjectMap(ConfigBuilder config){
        StrategyConfig strategyConfig = config.getStrategyConfig();
        Map<String, Object> objectMap = new HashMap<>();
        Map<String, String> packageMap = new HashMap<>(config.getPackageConfig().getPackageInfo());
        objectMap.put("config", config);
        objectMap.put("package", packageMap);
        GlobalConfig globalConfig = config.getGlobalConfig();
        objectMap.put("author", globalConfig.getAuthor());
        objectMap.put("kotlin", globalConfig.isKotlin());
        objectMap.put("swagger", globalConfig.isSwagger());
        objectMap.put("date", globalConfig.getCommentDate());
        objectMap.put("outputDir", globalConfig.getOutputDir());
        return objectMap;
    }

    public List beforeOutputFile(TableInfo tableInfo, Map<String, Object> objectMap){
        List result = JsUtil.invokeInClasspath("classpath:"+Paths.get(generatorName, "beforeOutputFile.js").toString(),
                "defineCustomFiles"
                , new Object[]{objectMap}, List.class, new JsUtil.CustomizeFunction() {
            @Override
            public void customize(Context context, Scriptable scope) {
                JsUtil.putIntoScope(this, "$engine", scope);
                JsUtil.putIntoScope(getConfigBuilder(), "$config", scope);
                JsUtil.putIntoScope(tableInfo, "$tableInfo", scope);
                JsUtil.putIntoScope(objectMap, "$context", scope);
            }
        });
        return result;
    }

    public static List<Object> nativeArrayToList(NativeArray nativeArray)
    {
        List<Object> array = new ArrayList<>();

        for (Object o : nativeArray)
        {
            if (o instanceof NativeObject)
            {
                array.add(nativeObjectToMap((NativeObject) o));
            }
            else if (o instanceof NativeArray)
            {
                array.add(nativeArrayToList((NativeArray) o));
            }
            else
            {
                array.add(o.toString());
            }
        }

        return array;
    }

    public static Map<String, Object> nativeObjectToMap(NativeObject nativeObject){
        Map<String, Object> map = new HashMap<>();
        Set<Map.Entry<Object, Object>> entrySet = nativeObject.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet)
        {
            if (entry.getValue() instanceof String)
            {
                map.put(entry.getKey().toString(), entry.getValue().toString());
            }
            else if (entry.getValue() instanceof NativeArray)
            {
                map.put(entry.getKey().toString(), nativeArrayToList((NativeArray) entry.getValue()));
            }
            else if (entry.getValue() instanceof NativeObject)
            {
                map.put(entry.getKey().toString(), nativeObjectToMap((NativeObject) entry.getValue()));
            }else{
                map.put(entry.getKey().toString(), entry.getValue());
            }
        }

        return map;
    }

    public void outputCustomFile(List<NativeObject> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
        customFile.forEach(f -> {
            String fpkg = f.get("package").toString();
            Map<String, String> packageMap = new HashMap((Map<String, String>) objectMap.get("package"));
            packageMap.put(fpkg.toUpperCase(), makePackage(getConfigBuilder(), fpkg));
            objectMap.put("package", packageMap);

            ConfigBuilder config = getConfigBuilder();
            Map<String, Object> args = new HashMap<>(objectMap);
            args.put("$config", config);
            args.put("$engine", this);
            if(f.containsKey("fieldMap")) {
                args.put("fieldMap", nativeObjectToMap((NativeObject) f.get("fieldMap")));
            }
            File file = new File(makeOutputPath(config, fpkg, f.get("file").toString()+suffixJavaOrKt()));
            boolean override = Boolean.valueOf(f.get("override").toString());
            outputFile(file,
                    args,
                    f.get("template").toString(),
                    override
                    );
        });
    }

    public String makeTemplatePath(String templatePath){
        return Paths.get( generatorName, templatePath).toString();
    }

    public AbstractTemplateEngine batchOutput() {
        try {
            ConfigBuilder config = this.getConfigBuilder();
            List<TableInfo> tableInfoList = config.getTableInfoList();
            tableInfoList.forEach(tableInfo -> {
                Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);

                // 输出自定义文件
                List customFileList =  beforeOutputFile(tableInfo, objectMap);
                outputCustomFile(customFileList, tableInfo, objectMap);

                // entity
                outputEntity(tableInfo, objectMap);
                // mapper and xml
                outputMapper(tableInfo, objectMap);
                // service
                outputService(tableInfo, objectMap);
                // controller
                outputController(tableInfo, objectMap);
            });

            Map<String, Object> objectMap = makeSimpleObjectMap(config);
            JsUtil.runInClasspath("classpath:"+generatorName+"/main.js", objectMap, null, (context, scope) -> {
                JsUtil.putIntoScope(this, "$engine", scope);
                JsUtil.putIntoScope(config, "$config", scope);
            });

        } catch (Exception e) {
            throw new RuntimeException("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }

    public void outputFile(File file, Map<String, Object> objectMap, String templatePath, boolean fileOveride){
        log.info("Generating(Override:{}) {} with args: {}", fileOveride, file, objectMap);
        if(isClasspathResourceExist(makeTemplatePath(templatePath))){
            super.outputFile(file, objectMap, makeTemplatePath(templatePath), fileOveride);
        }else{
            super.outputFile(file, objectMap, templatePath, fileOveride);
        }
    }

    public void outputFile(String file, Map<String, Object> objectMap, String templatePath, boolean fileOveride){
        outputFile(new File(file), objectMap, templatePath, fileOveride);
    }

    public boolean isClasspathResourceExist(String path){
        try {
            URL file = ResourceUtils.getURL("classpath:"+path);
            InputStream inputStream = file.openStream();
            if(inputStream!=null){
                inputStream.close();
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}

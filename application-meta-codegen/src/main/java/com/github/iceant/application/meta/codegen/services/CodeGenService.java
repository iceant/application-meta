package com.github.iceant.application.meta.codegen.services;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.github.iceant.application.meta.codegen.config.CodeGenProperties;
import org.springframework.stereotype.Service;

@Service
public class CodeGenService {

    public void execute(String name, CodeGenProperties.CodeGeneratorConfig config){
        new AutoGenerator(config.buildDataSourceConfig())
                // 全局配置
                .global(config.buildGlobalConfig())
                // 包配置
                .packageInfo(config.buildPackageConfig())
                // 策略配置
                .strategy(config.buildStrategyConfig())
                // 注入配置
                .injection(config.getInjectionConfig())
                // 模板配置
                .template(config.buildTemplateConfig())
                // 执行
                .execute(((CustomizeBeetlTemplateEngine)config.getTemplateEngine()).setGeneratorName(name));
    }

//    public void run(){
//        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create("jdbc:sqlite:appcfg.db", null, null);
//        fastAutoGenerator
//                .globalConfig(builder -> {
//                    builder.author("Chen Peng") // 设置作者
////                            .enableSwagger() // 开启 swagger 模式
//                            .disableOpenDir()
////                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("./application-configurator-console/src/main/java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    Map<OutputFile, String> pathInfo = new HashMap<>();
//                    pathInfo.put(OutputFile.xml, "./application-configurator-console/src/main/resources/mapper");
//                    pathInfo.put(OutputFile.other, "./application-configurator-console/src/main/java/");
//
//                    builder.parent("com.github.iceant.application.configurator.console") // 设置父包名
//                            .moduleName("storage") // 设置父包模块名
//                            .pathInfo(
//                                    Collections.singletonMap(OutputFile.xml, "./application-configurator-console/src/main/resources/mapper")
//                            ); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addExclude("sqlite_sequence");
//                    builder.entityBuilder()
//                            .enableTableFieldAnnotation()
//                            .enableLombok()
//                            .enableChainModel()
//                            .enableColumnConstant()
//                            .fileOverride();
//                    builder.controllerBuilder()
//                            .enableRestStyle()
//                            .fileOverride();
//                })
//                .templateConfig(builder -> {
//                    builder.xml("")
////                            .controller("")
//                    ;
//                })
//                .templateEngine(new CustomizeBeetlTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .injectionConfig(builder -> {
//                    builder.fileOverride();
//
//                    Map<String, Object> customMap = new HashMap<>();
//                    customMap.put("DTO.java.path", "dto");
//                    customMap.put("ApiResponse.java.path", "controller");
//                    customMap.put("MapStruct.java.path", "mapstruct");
//                    builder.customMap(customMap);
//
//                    Map<String, String> customFile = new HashMap<>();
//                    customFile.put("DTO.java", "templates/dto.java.btl");
//                    customFile.put("MapStruct.java", "templates/mapstruct.java.btl");
//                    builder.customFile(customFile);
//
//                    builder.beforeOutputFile(new BiConsumer<TableInfo, Map<String, Object>>() {
//                        @Override
//                        public void accept(TableInfo tableInfo, Map<String, Object> objectMap) {
//                            Map<String, String> packageMap = (Map<String, String>) objectMap.get("package");
//                            Map<String, String> modifiableMap = new HashMap<>(packageMap);
//                            modifiableMap.put("DTO", packageMap.get("Parent")+".dto"); // package.DTO
//                            modifiableMap.put("MAPSTRUCT", packageMap.get("Parent")+".mapstruct"); // package.MAPSTRUCT
//                            objectMap.put("package", modifiableMap);
//                        }
//                    });
//
//                })
//                .execute();
//    }
}

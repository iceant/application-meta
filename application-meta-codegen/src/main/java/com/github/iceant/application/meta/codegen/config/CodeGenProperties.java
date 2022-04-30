package com.github.iceant.application.meta.codegen.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.github.iceant.application.meta.codegen.services.CustomizeBeetlTemplateEngine;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.cache.Cache;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

@ConfigurationProperties(prefix = "codegen")
@Data
@Accessors(chain = true)
public class CodeGenProperties {

    @Data
    @Accessors(chain = true)
    public static class CodeGenDataSourceConfig{
        /**
         * 驱动连接的URL
         */
        String url;

        /**
         * 数据库连接用户名
         */
        String username;

        /**
         * 数据库连接密码
         */
        String password;
    }

    @Data
    @Accessors(chain = true)
    public static class CodeGenGlobalConfig{
        /**
         * 生成文件的输出目录【 windows:D://  linux or mac:/tmp 】
         */
        String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";

        /**
         * 是否覆盖已有文件（默认 false）（3.5.3版本会删除此方法）
         */
        @Deprecated
        boolean fileOverride;

        /**
         * 是否打开输出目录
         */
        boolean open = false;

        /**
         * 作者
         */
        String author = "作者";

        /**
         * 开启 Kotlin 模式（默认 false）
         */
        boolean kotlin;

        /**
         * 开启 swagger 模式（默认 false）
         */
        boolean swagger;

        /**
         * 时间类型对应策略
         */
        DateType dateType = DateType.TIME_PACK;
    }

    @Data
    @Accessors(chain = true)
    public static class CodeGenPackageConfig{
        /**
         * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
         */
        private String parent = "com.baomidou";

        /**
         * 父包模块名
         */
        private String moduleName = "";

        /**
         * Entity包名
         */
        private String entity = "entity";

        /**
         * Service包名
         */
        private String service = "service";

        /**
         * Service Impl包名
         */
        private String serviceImpl = "service.impl";

        /**
         * Mapper包名
         */
        private String mapper = "mapper";

        /**
         * Mapper XML包名
         */
        private String xml = "mapper.xml";

        /**
         * Controller包名
         */
        private String controller = "controller";

        /**
         * Other包名
         */
        private String other = "other";

        /**
         * 路径配置信息
         */
        private Map<OutputFile, String> pathInfo;
    }

    @Data
    @Accessors(chain = true)
    public static class CodeGenTemplateConfig{
        /**
         * 设置实体模板路径
         */
        private String entity;

        /**
         * 设置实体模板路径(kotlin模板)
         */
        private String entityKt;

        /**
         * 设置控制器模板路径
         */
        private String controller;

        /**
         * 设置Mapper模板路径
         */
        private String mapper;

        /**
         * 设置MapperXml模板路径
         */
        private String xml;

        /**
         * 设置Service模板路径
         */
        private String service;

        /**
         * 设置ServiceImpl模板路径
         */
        private String serviceImpl;

        /**
         * 是否禁用实体模板（默认 false）
         */
        private boolean disableEntity=false;

        public CodeGenTemplateConfig(){
            this.entity = ConstVal.TEMPLATE_ENTITY_JAVA;
            this.entityKt = ConstVal.TEMPLATE_ENTITY_KT;
            this.controller = ConstVal.TEMPLATE_CONTROLLER;
            this.mapper = ConstVal.TEMPLATE_MAPPER;
            this.xml = ConstVal.TEMPLATE_XML;
            this.service = ConstVal.TEMPLATE_SERVICE;
            this.serviceImpl = ConstVal.TEMPLATE_SERVICE_IMPL;
        }
    }

    @Data
    @Accessors(chain = true)
    public static class CodeGenEntityConfig{
        /**
         * 名称转换
         */
        private INameConvert nameConvert=null;

        /**
         * 自定义继承的Entity类全称，带包名
         */
        private String superClass;

        /**
         * 自定义基础的Entity类，公共字段
         */
        private final Set<String> superEntityColumns = new HashSet<>();

        /**
         * 自定义忽略字段
         * https://github.com/baomidou/generator/issues/46
         */
        private final Set<String> ignoreColumns = new HashSet<>();

        /**
         * 实体是否生成 serialVersionUID
         */
        private boolean serialVersionUID = true;

        /**
         * 【实体】是否生成字段常量（默认 false）<br>
         * -----------------------------------<br>
         * public static final String ID = "test_id";
         */
        private boolean columnConstant;

        /**
         * 【实体】是否为链式模型（默认 false）<br>
         * -----------------------------------<br>
         * public User setName(String name) { this.name = name; return this; }
         *
         * @since 3.3.2
         */
        private boolean chain;

        /**
         * 【实体】是否为lombok模型（默认 false）<br>
         * <a href="https://projectlombok.org/">document</a>
         */
        private boolean lombok;

        /**
         * Boolean类型字段是否移除is前缀（默认 false）<br>
         * 比如 : 数据库字段名称 : 'is_xxx',类型为 : tinyint. 在映射实体的时候则会去掉is,在实体类中映射最终结果为 xxx
         */
        private boolean removeIsPrefix;

        /**
         * 是否生成实体时，生成字段注解（默认 false）
         */
        private boolean tableFieldAnnotation;

        /**
         * 乐观锁字段名称(数据库字段)
         *
         * @since 3.5.0
         */
        private String versionColumnName;

        /**
         * 乐观锁属性名称(实体字段)
         *
         * @since 3.5.0
         */
        private String versionPropertyName;

        /**
         * 逻辑删除字段名称(数据库字段)
         *
         * @since 3.5.0
         */
        private String logicDeleteColumnName;

        /**
         * 逻辑删除属性名称(实体字段)
         *
         * @since 3.5.0
         */
        private String logicDeletePropertyName;

        /**
         * 表填充字段
         */
        private final List<IFill> tableFillList = new ArrayList<>();

        /**
         * 数据库表映射到实体的命名策略，默认下划线转驼峰命名
         */
        private NamingStrategy naming = NamingStrategy.underline_to_camel;

        /**
         * 数据库表字段映射到实体的命名策略
         * <p>未指定按照 naming 执行</p>
         */
        private NamingStrategy columnNaming = NamingStrategy.underline_to_camel;

        /**
         * 开启 ActiveRecord 模式（默认 false）
         *
         * @since 3.5.0
         */
        private boolean activeRecord;

        /**
         * 指定生成的主键的ID类型
         *
         * @since 3.5.0
         */
        private IdType idType;

        /**
         * 转换输出文件名称
         *
         * @since 3.5.0
         */
        private ConverterFileName converterFileName = (entityName -> entityName);

        /**
         * 是否覆盖已有文件（默认 false）
         *
         * @since 3.5.2
         */
        private boolean fileOverride;
    }

    @Data
    public static class CodeGenControllerConfig{
        /**
         * 生成 <code>@RestController</code> 控制器（默认 false）
         * <pre>
         *      <code>@Controller</code> -> <code>@RestController</code>
         * </pre>
         */
        private boolean restStyle;

        /**
         * 驼峰转连字符（默认 false）
         * <pre>
         *      <code>@RequestMapping("/managerUserActionHistory")</code> -> <code>@RequestMapping("/manager-user-action-history")</code>
         * </pre>
         */
        private boolean hyphenStyle;

        /**
         * 自定义继承的Controller类全称，带包名
         */
        private String superClass;

        /**
         * 转换输出控制器文件名称
         *
         * @since 3.5.0
         */
        private ConverterFileName converterFileName = (entityName -> entityName + ConstVal.CONTROLLER);

        /**
         * 是否覆盖已有文件（默认 false）
         *
         * @since 3.5.2
         */
        private boolean fileOverride;
    }

    @Data
    public static class CodeGenMapperConfig{
        /**
         * 自定义继承的Mapper类全称，带包名
         */
        private String superClass = ConstVal.SUPER_MAPPER_CLASS;

        /**
         * 是否添加 @Mapper 注解（默认 false）
         *
         * @since 3.5.1
         */
        private boolean mapperAnnotation=false;

        /**
         * 是否开启BaseResultMap（默认 false）
         *
         * @since 3.5.0
         */
        private boolean baseResultMap=false;

        /**
         * 是否开启baseColumnList（默认 false）
         *
         * @since 3.5.0
         */
        private boolean baseColumnList=false;

        /**
         * 转换输出Mapper文件名称
         *
         * @since 3.5.0
         */
        private ConverterFileName converterMapperFileName = (entityName -> entityName + ConstVal.MAPPER);

        /**
         * 转换输出Xml文件名称
         *
         * @since 3.5.0
         */
        private ConverterFileName converterXmlFileName = (entityName -> entityName + ConstVal.MAPPER);

        /**
         * 是否覆盖已有文件（默认 false）
         *
         * @since 3.5.2
         */
        private boolean fileOverride;

        /**
         * 设置缓存实现类
         *
         * @since 3.5.0
         */
        private Class<? extends Cache> cache;

    }

    @Data
    public static class CodeGenServiceConfig{
        /**
         * 自定义继承的Service类全称，带包名
         */
        private String superServiceClass = ConstVal.SUPER_SERVICE_CLASS;

        /**
         * 自定义继承的ServiceImpl类全称，带包名
         */
        private String superServiceImplClass = ConstVal.SUPER_SERVICE_IMPL_CLASS;

        public String getSuperServiceClass() {
            return superServiceClass;
        }

        public String getSuperServiceImplClass() {
            return superServiceImplClass;
        }

        /**
         * 转换输出Service文件名称
         *
         * @since 3.5.0
         */
        private ConverterFileName converterServiceFileName = (entityName -> "I" + entityName + ConstVal.SERVICE);

        /**
         * 转换输出ServiceImpl文件名称
         *
         * @since 3.5.0
         */
        private ConverterFileName converterServiceImplFileName = (entityName -> entityName + ConstVal.SERVICE_IMPL);

        /**
         * 是否覆盖已有文件（默认 false）
         *
         * @since 3.5.2
         */
        private boolean fileOverride;
    }

    @Data
    public static class CodeGenStrategyConfig{
        /**
         * 是否大写命名（默认 false）
         */
        private boolean isCapitalMode;

        /**
         * 是否跳过视图（默认 false）
         */
        private boolean skipView;

        /**
         * 过滤表前缀
         * example: addTablePrefix("t_")
         * result: t_simple -> Simple
         */
        private final Set<String> tablePrefix = new HashSet<>();

        /**
         * 过滤表后缀
         * example: addTableSuffix("_0")
         * result: t_simple_0 -> Simple
         */
        private final Set<String> tableSuffix = new HashSet<>();

        /**
         * 过滤字段前缀
         * example: addFieldPrefix("is_")
         * result: is_deleted -> deleted
         */
        private final Set<String> fieldPrefix = new HashSet<>();

        /**
         * 过滤字段后缀
         * example: addFieldSuffix("_flag")
         * result: deleted_flag -> deleted
         */
        private final Set<String> fieldSuffix = new HashSet<>();

        /**
         * 需要包含的表名，允许正则表达式（与exclude二选一配置）<br/>
         * 当{@link #enableSqlFilter}为true时，正则表达式无效.
         */
        private final Set<String> include = new HashSet<>();

        /**
         * 需要排除的表名，允许正则表达式<br/>
         * 当{@link #enableSqlFilter}为true时，正则表达式无效.
         */
        private final Set<String> exclude = new HashSet<>();

        /**
         * 启用sql过滤，语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
         *
         * @since 3.3.1
         */
        private boolean enableSqlFilter = true;

        /**
         * 启用 schema 默认 false
         */
        private boolean enableSchema;

        /**
         * 包含表名
         *
         * @since 3.3.0
         */
        private LikeTable likeTable;

        /**
         * 不包含表名
         *
         * @since 3.3.0
         */
        private LikeTable notLikeTable;

        CodeGenEntityConfig entityConfig = new CodeGenEntityConfig();
        CodeGenControllerConfig controllerConfig = new CodeGenControllerConfig();
        CodeGenMapperConfig mapperConfig = new CodeGenMapperConfig();
        CodeGenServiceConfig serviceConfig = new CodeGenServiceConfig();
    }

    @Data
    @Accessors(chain = true)
    public static class CodeGeneratorConfig{

        CodeGenDataSourceConfig datasourceConfig = new CodeGenDataSourceConfig();
        CodeGenGlobalConfig globalConfig = new CodeGenGlobalConfig();
        CodeGenPackageConfig packageConfig = new CodeGenPackageConfig();
        CodeGenTemplateConfig templateConfig = new CodeGenTemplateConfig();
        CodeGenStrategyConfig strategyConfig = new CodeGenStrategyConfig();
        InjectionConfig injectionConfig = new InjectionConfig();
        AbstractTemplateEngine templateEngine = new CustomizeBeetlTemplateEngine();

        ////////////////////////////////////////////////////////////////////////////////
        ////
        public InjectionConfig getInjectionConfig(){
            return injectionConfig;
        }

        public AbstractTemplateEngine getTemplateEngine(){
            return templateEngine;
        }

        public DataSourceConfig buildDataSourceConfig(){
            return new DataSourceConfig.Builder(datasourceConfig.url, datasourceConfig.username, datasourceConfig.password).build();
        }

        public GlobalConfig buildGlobalConfig(){
            GlobalConfig.Builder builder =  new GlobalConfig.Builder();

            builder.outputDir(globalConfig.getOutputDir());
            if(globalConfig.isFileOverride()) {
                builder.fileOverride();
            }
            if(!globalConfig.isOpen()){
                builder.disableOpenDir();
            }
            builder.author(globalConfig.getAuthor());
            if(globalConfig.isKotlin()){
                builder.enableKotlin();
            }
            if(globalConfig.isSwagger()){
                builder.enableSwagger();
            }
            builder.dateType(globalConfig.getDateType());
            return builder.build();
        }

        public PackageConfig buildPackageConfig(){
            PackageConfig.Builder builder = new PackageConfig.Builder();
            builder.parent(packageConfig.parent);
            builder.moduleName(packageConfig.moduleName);
            builder.entity(packageConfig.entity);
            builder.service(packageConfig.service);
            builder.serviceImpl(packageConfig.serviceImpl);
            builder.mapper(packageConfig.mapper);
            builder.xml(packageConfig.xml);
            builder.controller(packageConfig.controller);
            builder.other(packageConfig.other);
            builder.pathInfo(packageConfig.pathInfo);
            return builder.build();
        }

        public TemplateConfig buildTemplateConfig(){
            TemplateConfig.Builder builder = new TemplateConfig.Builder();
            builder.entity(templateConfig.entity);
            builder.entityKt(templateConfig.entityKt);
            builder.controller(templateConfig.controller);
            builder.mapper(templateConfig.mapper);
            builder.xml(templateConfig.xml);
            builder.service(templateConfig.service);
            builder.serviceImpl(templateConfig.serviceImpl);
            if(templateConfig.disableEntity){
                builder.disable(TemplateType.ENTITY);
            }
            return builder.build();
        }

        private <T> List<T> setToList(Set<T> set){
            List<T> list = new ArrayList<>(set);
            return list;
        }

        public StrategyConfig buildStrategyConfig(){
            StrategyConfig.Builder builder = new StrategyConfig.Builder();
            if(strategyConfig.isCapitalMode){
                builder.enableCapitalMode();
            }
            if(strategyConfig.isSkipView()){
                builder.enableSkipView();
            }
            builder.addTablePrefix(setToList(strategyConfig.tablePrefix));
            builder.addTableSuffix(setToList(strategyConfig.tableSuffix));
            builder.addFieldPrefix(setToList(strategyConfig.fieldPrefix));
            builder.addFieldSuffix(setToList(strategyConfig.fieldSuffix));
            builder.addInclude(setToList(strategyConfig.include));
            builder.addExclude(setToList(strategyConfig.exclude));
            if(!strategyConfig.enableSqlFilter){
                builder.disableSqlFilter();
            }
            if(strategyConfig.enableSchema) builder.enableSchema();
            builder.likeTable(strategyConfig.likeTable);
            builder.notLikeTable(strategyConfig.notLikeTable);

            Entity.Builder entityBuilder = builder.entityBuilder();
            CodeGenEntityConfig entityConfig = strategyConfig.entityConfig;
            if(entityConfig.nameConvert!=null)
                entityBuilder.nameConvert(entityConfig.nameConvert);
            entityBuilder.superClass(entityConfig.superClass);
            entityBuilder.addSuperEntityColumns(setToList(entityConfig.superEntityColumns));
            entityBuilder.addIgnoreColumns(setToList(entityConfig.ignoreColumns));
            if(!entityConfig.serialVersionUID){
                entityBuilder.disableSerialVersionUID();
            }
            if(entityConfig.columnConstant){
                entityBuilder.enableColumnConstant();
            }
            if(entityConfig.chain){
                entityBuilder.enableChainModel();
            }
            if(entityConfig.lombok){
                entityBuilder.enableLombok();
            }
            if(entityConfig.removeIsPrefix){
                entityBuilder.enableRemoveIsPrefix();
            }
            if(entityConfig.tableFieldAnnotation){
                entityBuilder.enableTableFieldAnnotation();
            }
            entityBuilder.versionColumnName(entityConfig.versionColumnName);
            entityBuilder.logicDeleteColumnName(entityConfig.logicDeleteColumnName);
            entityBuilder.logicDeletePropertyName(entityConfig.logicDeletePropertyName);
            entityBuilder.naming(entityConfig.naming);
            entityBuilder.columnNaming(entityConfig.columnNaming);
            if(entityConfig.activeRecord){
                entityBuilder.enableActiveRecord();
            }
            entityBuilder.idType(entityConfig.idType);
            entityBuilder.convertFileName(entityConfig.converterFileName);
            if(entityConfig.fileOverride) entityBuilder.fileOverride();

            CodeGenControllerConfig controllerConfig = strategyConfig.getControllerConfig();
            Controller.Builder cb = builder.controllerBuilder();
            if(controllerConfig.restStyle){
                cb.enableRestStyle();
            }
            if(controllerConfig.hyphenStyle)
                cb.enableHyphenStyle();

            cb.superClass(controllerConfig.superClass);
            cb.convertFileName(controllerConfig.converterFileName);
            if(controllerConfig.fileOverride){
                cb.fileOverride();
            }


            CodeGenMapperConfig mc = strategyConfig.mapperConfig;
            Mapper.Builder mb = builder.mapperBuilder();
            mb.superClass(mc.superClass);
            if(mc.mapperAnnotation)
                mb.enableMapperAnnotation();
            if(mc.baseResultMap){
                mb.enableBaseResultMap();
            }
            if(mc.baseColumnList){
                mb.enableBaseColumnList();
            }
            mb.convertMapperFileName(mc.converterMapperFileName);
            mb.convertXmlFileName(mc.converterXmlFileName);
            if(mc.fileOverride)
                mb.fileOverride();
            mb.cache(mc.cache);

            CodeGenServiceConfig sc = strategyConfig.serviceConfig;
            Service.Builder sb = builder.serviceBuilder();

            sb.superServiceClass(sc.superServiceClass);
            sb.superServiceImplClass(sc.superServiceImplClass);
            sb.convertServiceFileName(sc.converterServiceFileName);
            sb.convertServiceImplFileName(sc.converterServiceImplFileName);
            if(sc.fileOverride){
                sb.fileOverride();
            }

            return builder.build();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    Map<String, CodeGeneratorConfig> generators = new HashMap<>();
}

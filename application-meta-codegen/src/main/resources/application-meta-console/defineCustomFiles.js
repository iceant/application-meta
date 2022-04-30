function defineCustomFiles(tableInfo, objectMap){
    var files = [];
    files.push({
        package:"dto",
        file: $tableInfo.getEntityName()+"DTO",
        template:"templates/dto.java.btl",
        override: true,
        fieldMap:{
            "id":"Long",
            "userFriendlyName":"com.github.iceant.application.meta.console.domain.LocaleString",
            "description":"com.github.iceant.application.meta.console.domain.LocaleString"
        }
    });
    files.push({
        package:"vo",
        file: $tableInfo.getEntityName()+"VO",
        template:"templates/vo.java.btl",
        override: true,
        fieldMap:{
            "id":"Long",
            "userFriendlyName":"com.github.iceant.application.meta.console.domain.LocaleString",
            "description":"com.github.iceant.application.meta.console.domain.LocaleString"
        }
    });
    files.push({
        package:"mapstruct",
        file: $tableInfo.getEntityName()+"MapStruct",
        template:"templates/mapstruct.java.btl",
        override: true
    });
    return files;
}

/*
{
    superControllerClassPackage=null,
    date=2022-03-31,
    superServiceImplClassPackage=com.baomidou.mybatisplus.extension.service.impl.ServiceImpl,
    baseResultMap=false,
    superMapperClass=BaseMapper,
    schemaName=,
    superControllerClass=null,
    swagger=false,
    activeRecord=false,
    superServiceClass=IService,
    controllerMappingHyphen=t-data-type,
    controllerMappingHyphenStyle=false,
    superServiceImplClass=ServiceImpl,
    table=com.baomidou.mybatisplus.generator.config.po.TableInfo@7636823f,
    $engine=CustomizeBeetlTemplateEngine(generatorName=application-configurator-console),
    idType=null,
    package={
        Entity=com.github.iceant.application.configurator.console.storage.entity,
        Mapper=com.github.iceant.application.configurator.console.storage.mapper,
        Parent=com.github.iceant.application.configurator.console.storage,
        ModuleName=storage,
        Xml=com.github.iceant.application.configurator.console.storage.mapper.xml,
        ServiceImpl=com.github.iceant.application.configurator.console.storage.service.impl,
        Service=com.github.iceant.application.configurator.console.storage.service,
        Controller=com.github.iceant.application.configurator.console.storage.controller,
        Other=com.github.iceant.application.configurator.console.storage.other},
    baseColumnList=false,
    author=Chen Peng,
    superMapperClassPackage=com.baomidou.mybatisplus.core.mapper.BaseMapper,
    entityLombokModel=true,
    kotlin=false,
    restControllerStyle=true,
    chainModel=false,
    superServiceClassPackage=com.baomidou.mybatisplus.extension.service.IService,
    entityBuilderModel=false,
    entitySerialVersionUID=true,
    versionFieldName=null,
    mapperAnnotation=false,
    entityBooleanColumnRemoveIsPrefix=false,
    $config=com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder@a0db585,
    logicDeleteFieldName=null,
    entityColumnConstant=true,
    enableCache=false,
    config=com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder@a0db585,
    superEntityClass=null,
    entity=TDataType}
*/
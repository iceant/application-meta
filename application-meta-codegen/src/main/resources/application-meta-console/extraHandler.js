//{
// date=2022-03-31,
// outputDir=./application-configurator-console/src/main/java,
// package={
//      Entity=com.github.iceant.application.configurator.console.storage.entity,
//      Mapper=com.github.iceant.application.configurator.console.storage.mapper,
//      Parent=com.github.iceant.application.configurator.console.storage,
//      ModuleName=storage,
//      Xml=com.github.iceant.application.configurator.console.storage.mapper.xml,
//      ServiceImpl=com.github.iceant.application.configurator.console.storage.service.impl,
//      Service=com.github.iceant.application.configurator.console.storage.service,
//      Controller=com.github.iceant.application.configurator.console.storage.controller,
//      ApiResponse=com.github.iceant.application.configurator.console.storage.controller,
//      Other=com.github.iceant.application.configurator.console.storage.other
//  },
//  author=Chen Peng,
//  kotlin=false,
//  config=com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder@66236a0a,
//  swagger=false
//}

let pkg = $args.get("package");
pkg.put("ApiResponse", pkg.get("Controller"));
$engine.outputFile($engine.makeOutputPath(config, "controller", "ApiResponse.java"), $args, 'templates/ApiResponse.java.btl', true);
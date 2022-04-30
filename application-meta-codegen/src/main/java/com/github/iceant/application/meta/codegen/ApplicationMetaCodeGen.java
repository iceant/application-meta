package com.github.iceant.application.meta.codegen;

import com.github.iceant.application.meta.codegen.config.CodeGenProperties;
import com.github.iceant.application.meta.codegen.services.CodeGenService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationMetaCodeGen {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ApplicationMetaCodeGen.class).web(WebApplicationType.NONE)
                .run(args);
        CodeGenService service = context.getBean(CodeGenService.class);
        CodeGenProperties properties = context.getBean(CodeGenProperties.class);
        for(String key : properties.getGenerators().keySet()){
            CodeGenProperties.CodeGeneratorConfig config = properties.getGenerators().get(key);
            service.execute(key, config);
        }
    }
}

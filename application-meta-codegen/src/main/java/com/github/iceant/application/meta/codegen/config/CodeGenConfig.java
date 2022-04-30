package com.github.iceant.application.meta.codegen.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({CodeGenProperties.class})
public class CodeGenConfig {
    final CodeGenProperties properties;

    public CodeGenConfig(CodeGenProperties properties) {
        this.properties = properties;
    }
}

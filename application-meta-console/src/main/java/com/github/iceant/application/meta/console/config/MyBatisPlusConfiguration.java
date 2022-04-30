package com.github.iceant.application.meta.console.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.github.iceant.application.meta.console.mybatis.SQLiteLocalDateTimeTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "com.github.iceant.application.meta.console.storage.mapper")
public class MyBatisPlusConfiguration{

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return configuration -> {
            configuration.getTypeHandlerRegistry().register(SQLiteLocalDateTimeTypeHandler.class);
        };
    }

}

package com.github.iceant.application.meta.console.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
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

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //  插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.SQLITE);
        // 添加分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

}

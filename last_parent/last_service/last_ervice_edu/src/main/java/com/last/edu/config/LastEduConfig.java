package com.last.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 22514
 */
@Configuration
public class LastEduConfig {

    /**
     * 逻辑删除插件
     *
     * @return LogicSqlInjector
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}

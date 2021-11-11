package com.lenovo.it.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.lenovo.it.task.mapper"})//扫描mapper接口
public class MybatisPlusConfig {
    /**
     * 配置分页查询插件：PaginationInterceptor
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}

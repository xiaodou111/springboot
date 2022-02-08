package com.qingge.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //设置放行哪些原始域
                .allowedOriginPatterns("*")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些请求方式
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE")
                .maxAge(300)
                //放行哪些原始请求头部信息
                .allowedHeaders("*");
    }
}


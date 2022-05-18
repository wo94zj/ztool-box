package com.ztool.box.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhaoj
 * @Date 2022/5/13 15:01
 */
@Configuration
public class FilterConfig {

    @Bean
    @ConditionalOnWebApplication
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // 是否允许发送Cookie信息
        config.setMaxAge(Duration.ofSeconds(3600)); // 预检请求的有效期
        config.addAllowedOrigin("*");   // 开放哪些ip、端口、域名的访问权限，星号表示开放所有域
        config.addAllowedHeader("*");   // 允许HTTP请求中的携带哪些Header信息
        config.addAllowedMethod("*");   // 开放哪些Http方法，允许跨域访问
        source.registerCorsConfiguration("/**", config);    // 添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    //过滤器配置
    @Bean
    public FilterRegistrationBean<BasicFilter> initFilter() {
        FilterRegistrationBean<BasicFilter> bean = new FilterRegistrationBean<>();
        BasicFilter filter = new BasicFilter();
        bean.setFilter(filter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        bean.setUrlPatterns(urlPatterns);
        bean.setOrder(1);
        return bean;
    }



}

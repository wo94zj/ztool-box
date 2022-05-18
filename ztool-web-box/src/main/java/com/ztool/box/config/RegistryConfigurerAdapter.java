package com.ztool.box.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.*;

@Configuration
public class RegistryConfigurerAdapter implements WebMvcConfigurer {

    //跨域配置
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        registry.addMapping("/**")
                .allowCredentials(true) // 是否允许发送Cookie信息
                .allowedOrigins("*")    // 开放哪些ip、端口、域名的访问权限，星号表示开放所有域
                .allowedHeaders("*")    // 允许HTTP请求中的携带哪些Header信息
                .allowedMethods("GET","POST", "PUT", "DELETE"); // /开放哪些Http方法，允许跨域访    问
    }*/

    /**
     * 类名                                      支持的JavaType           支持的MediaType
     * ByteArrayHttpMessageConverter            byte[]                  application/octet-stream, *\/*
     * StringHttpMessageConverter               String                  text/plain,*\/*
     * MappingJackson2HttpMessageConverter      Object                  application/json, application/*+json
     * AllEncompassingFormHttpMessageConverter  Map<K, List<?>>         application/x-www-form-urlencoded, multipart/form-data
     * SourceHttpMessageConverter               Source                  application/xml, text/xml, application/*+xml
     */
    @Bean
    public HttpMessageConverters jacksonConverters() {
        return new HttpMessageConverters(jacksonConverter());
    }

    MappingJackson2HttpMessageConverter jacksonConverter() {
        //JacksonHttpMessageConvertersConfiguration
        ObjectMapper om = Jackson2ObjectMapperBuilder.json()
                .serializerByType(Long.class, ToStringSerializer.instance)  // 前端不支持long类型
                .serializerByType(Long.TYPE, ToStringSerializer.instance)
                .build();
        //小驼峰
        om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);

        //反序列化的时候如果多了其他属性，不抛出异常，默认是抛出异常的
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //ALWAYS：序列化所有属性；NON_NULL：属性为Null的不进行序列化，只对pojo起作用，对map和list不起作用
        om.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //如果是空对象的时候,不抛异常；当实体类没有setter方法时，序列化不报错，返回一个空对象
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // json进行换行缩进等操作，默认不缩进
        om.enable(SerializationFeature.INDENT_OUTPUT);

        //json是否允许属性名没有引号 ，默认是false
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //json是否允许属性名为单引号 ，默认是false
        om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        List<MediaType> mediaTypeList = Arrays.asList(MediaType.APPLICATION_JSON,
                new MediaType("application", "*+json", Charset.forName("UTF-8")));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(om);
        converter.setSupportedMediaTypes(mediaTypeList);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        return converter;
    }

}

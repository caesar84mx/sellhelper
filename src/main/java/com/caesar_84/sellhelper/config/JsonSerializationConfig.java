package com.caesar_84.sellhelper.config;

import com.caesar_84.sellhelper.web.json.JacksonObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.Charset;

@Configuration
public class JsonSerializationConfig {
    @Bean
    MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(JacksonObjectMapper.getMapper());
        converter.setDefaultCharset(Charset.forName("utf-8"));

        return converter;
    }
}

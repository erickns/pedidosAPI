package com.ens.pedidosAPI.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

@Configuration
public class WebConfig {
    @Bean
    public MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter() {
        return new MappingJackson2XmlHttpMessageConverter();
    }
}

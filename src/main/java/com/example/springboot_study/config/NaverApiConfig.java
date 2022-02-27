package com.example.springboot_study.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "naver.openapi")
public class NaverApiConfig {

    private String movieUrl;
    private String clientId;
    private String clientSecret;
}

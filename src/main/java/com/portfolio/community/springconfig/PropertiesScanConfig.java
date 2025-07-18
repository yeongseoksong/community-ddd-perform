package com.portfolio.community.springconfig;


import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan("com.portfolio.community.payment")
public class PropertiesScanConfig {
}

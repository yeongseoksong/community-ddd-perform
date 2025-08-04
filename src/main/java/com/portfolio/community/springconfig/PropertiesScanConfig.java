package com.portfolio.community.springconfig;


import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan("com.portfolio.community.subscribe.payment")

public class PropertiesScanConfig {
}

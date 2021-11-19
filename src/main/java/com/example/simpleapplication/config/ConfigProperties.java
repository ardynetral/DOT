package com.example.simpleapplication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@Setter
@Getter
public class ConfigProperties {

    @Value("${spring.custom.config.urlRajaOngkir}")
    private String urlRajaOngkir;
}

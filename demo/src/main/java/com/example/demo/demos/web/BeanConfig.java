package com.example.demo.demos.web;

import org.pf4j.JarPluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public PluginBean pluginBean(){
        return new PluginBean();
    }
    @Bean
    public JarPluginManager jarPluginManager(){
        return  new JarPluginManager();
    }
}

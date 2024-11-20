package com.avirantEnterprises.information_collector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:///D:/information-collector/upload-dir/")
                .setCachePeriod(3600)  // optional cache duration
                .resourceChain(true);
    }
}



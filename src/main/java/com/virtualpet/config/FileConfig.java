package com.virtualpet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class FileConfig implements WebMvcConfigurer {

    @Value("${upload.sub.type.image}")
    private String subTypeImagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:///E:/Applications/virtual-pet-uploads/").setCachePeriod(0).resourceChain(true).addResolver(new PathResourceResolver());

    }

}

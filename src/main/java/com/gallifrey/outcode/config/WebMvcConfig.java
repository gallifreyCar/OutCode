package com.gallifrey.outcode.config;

import com.gallifrey.outcode.controller.interceptor.AlphaInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private AlphaInterceptor alphaInterceptor;

    @Autowired
    public void setAlphaInterceptor(AlphaInterceptor alphaInterceptor) {
        this.alphaInterceptor = alphaInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(alphaInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.jpeg")
                .addPathPatterns("/register", "/login");

    }

}

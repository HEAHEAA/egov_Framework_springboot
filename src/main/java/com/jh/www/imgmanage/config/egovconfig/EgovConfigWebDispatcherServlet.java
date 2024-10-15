package com.jh.www.imgmanage.config.egovconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : EgovConfigWebDispatcherServlet.java
 * @Description : DispatcherServlet 설정
 *
 * @author : 윤주호
 * @since  : 2021. 7. 20
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 7. 20    윤주호               최초 생성
 * </pre>
 *
 */
@Configuration
@ComponentScan(basePackages = "com.jh.www.imgmanage")
@RequiredArgsConstructor
public class EgovConfigWebDispatcherServlet implements WebMvcConfigurer {

    // -------------------------------------------------------------
    // RequestMappingHandlerMapping 설정 View Controller 추가
    // -------------------------------------------------------------

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("*");
            }
        };
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LogInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/login","/api/signUp","/api/refresh","/api/log/pageMoved","/api/layout/list/*",
                        "/api/layout/sensorList/*","/api/layout/foTime/*","/api/witem","/api/newsList/*","/api/did/files",
                        "/api/playlist","/api/did/list/*","/api/wdata","/api/node/data","/api/node/list");
    }
    @Bean
    public LogInterceptor LogInterceptor() {
        return new LogInterceptor();
    }
}

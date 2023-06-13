/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.swp391.demo.config;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 *
 * @author lnhtr
 */

@Configuration
//gi do
public class CorsConfig implements WebMvcConfigurer{
   
//    @Bean
//    public WebMvcConfigurer corsConfiguer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*")
//                        .allowedOrigins("*")
//                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
//            }
//        };
//    }
    
    
    @Override
    public void addCorsMappings (CorsRegistry resgitry){
        resgitry.addMapping("/**")
                .allowedOrigins("*");
    } 
}

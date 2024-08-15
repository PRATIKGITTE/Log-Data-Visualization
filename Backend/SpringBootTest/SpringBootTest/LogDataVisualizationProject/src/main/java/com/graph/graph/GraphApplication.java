package com.graph.graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The main entry point for the Spring Boot application.
 */
@SpringBootApplication
public class GraphApplication {

    /**
     * The main method to start the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);
    }
//    @Configuration
//    class WebConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/**")
//                    .addResourceLocations("file:/D:/PROJECT/ALL_IMP_To_Project/csv_File_Generated/");
//        }
//}
}

package com.ops.base.education.project.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class EduProjectManagerCORSConfiguration {
  @Bean
  public WebMvcConfigurer corsConfigurer(){
    return new WebMvcConfigurer() {
      /**
       * Configure cross origin requests processing.
       *
       * @param registry
       * @since 4.2
       */
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
      }
    };
  }
}

package database.doktalk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 URL 패턴에 대해 CORS 허용
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000") // 허용할 프론트엔드 URL
                .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

package com.example.redis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Redis Pub/Sub Project",
                description = "Redis Pub/Sub 테스트 프로젝트입니다."
        )
)
public class SwaggerConfig {

}

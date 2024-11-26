package br.com.testpge.restaurantmanager.shared.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().
                        title("api do restaurante")
                        .description("")
                        .summary("apipara de gerenciamneto do restaurante")
                        .version("0.0.1")
                )
                .servers(List.of(new Server().description("LOCAL")
                        .url("http://localhost:8082"))
                );
    }
}

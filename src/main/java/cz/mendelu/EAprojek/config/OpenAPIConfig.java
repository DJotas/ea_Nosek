package cz.mendelu.EAprojek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenAPIConfig {



    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fuel Data API")
                        .description("API for managing fuel data, manufacturers, and statistics")
                        .version("1.0")
                );
    }



}

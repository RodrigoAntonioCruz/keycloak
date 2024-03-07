package com.example.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class OpenApiConfiguration {

    private final OpenApiPropertiesConfiguration environment;
    @Bean
    public OpenAPI customOpenAPI() {

          return new OpenAPI()
                  .components(new Components())
                  .info(new Info()
                  .title("Users")
                  .description("API responsável por gerenciar as informações dos usuários")
                  .version(environment.getAppVersion()))
                  .tags(List.of(new Tag().name("Users").description("Endpoints responsáveis por gerenciar as informações dos usuários")
                  )
          );
    }
}
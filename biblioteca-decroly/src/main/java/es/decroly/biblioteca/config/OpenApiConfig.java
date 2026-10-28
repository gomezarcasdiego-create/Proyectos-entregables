package es.decroly.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Api de la Biblioteca Decroly",
        version = "1.0",
        description = "Tienda de libros clasicos"
))

public class OpenApiConfig {
}
package es.decroly.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "API Biblioteca Decroly",
        version = "1.0.0",
        description = "API REST para gestionar las categorías y los libros de la Biblioteca Decroly",
        contact = @Contact(name = "Proyecto Biblioteca Decroly")
))
public class OpenApiConfig {
}

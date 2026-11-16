package es.decroly.gimnasio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "API Gimnasio Decroly",
        version = "1.0.0",
        description = "API REST para gestionar las actividades del gimanasio Decroly",
        contact = @Contact(name = "Proyecto Gimnasio Decroly")
))
public class OpenApiConfig {
}

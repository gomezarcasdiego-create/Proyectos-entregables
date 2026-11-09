package es.decroly.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Categoría de libros de la biblioteca")
public class Categoria {
    @Schema(description = "Identificador de la categoría", example = "1")
    private Long id;

    @Schema(description = "Nombre obligatorio, máximo 50 caracteres y único sin distinguir mayúsculas", example = "Novela", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String nombre;

    @Schema(description = "Descripción de la categoría", example = "Libros de ficción y novelas")
    private String descripcion;
}

package es.decroly.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de entrada para crear o modificar una categoría")
public class CategoriaPeticionDto {
    @Schema(description = "Nombre obligatorio, máximo 50 caracteres y único sin distinguir mayúsculas", example = "Novela", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String nombre;

    @Schema(description = "Descripción de la categoría", example = "Libros de ficción y novelas")
    private String descripcion;
}

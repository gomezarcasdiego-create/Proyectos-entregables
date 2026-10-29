package es.decroly.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de entrada para crear o modificar un libro")
public class LibroPeticionDto {
    @Schema(description = "ISBN único de exactamente 13 cifras", example = "9788408172174", pattern = "\\d{13}", minLength = 13, maxLength = 13, requiredMode = Schema.RequiredMode.REQUIRED)
    private String isbn;

    @Schema(description = "Título obligatorio", example = "El principito", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;

    @Schema(description = "Autor obligatorio", example = "Antoine de Saint-Exupéry", requiredMode = Schema.RequiredMode.REQUIRED)
    private String autor;

    @Schema(description = "Año de publicación", example = "1943")
    private int anioPublicacion;

    @Schema(description = "Número total de ejemplares; debe ser al menos 1", example = "5", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private int ejemplares;

    @Schema(description = "Identificador de una categoría existente", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoriaId;

    @Schema(description = "Ejemplares disponibles", example = "5", minimum = "0")
    private int disponibles;
}

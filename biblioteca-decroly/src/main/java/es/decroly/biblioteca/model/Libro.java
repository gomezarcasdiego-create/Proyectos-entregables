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
@Schema(description = "Libro registrado en la biblioteca")
public class Libro{
    @Schema(description = "Identificador del libro", example = "1")
    private Long id;

    @Schema(description = "ISBN de 13 cifras", example = "9788408172174", pattern = "\\d{13}", minLength = 13, maxLength = 13)
    private String isbn;

    @Schema(description = "Título obligatorio del libro", example = "El principito", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;

    @Schema(description = "Autor obligatorio del libro", example = "Antoine de Saint-Exupéry", requiredMode = Schema.RequiredMode.REQUIRED)
    private String autor;

    @Schema(description = "Año de publicación", example = "1943")
    private int anioPublicacion;

    @Schema(description = "Número total de ejemplares; debe ser al menos 1", example = "5", minimum = "1")
    private int ejemplares;

    @Schema(description = "Número de ejemplares disponibles", example = "3", minimum = "0")
    private int disponibles;

    @Schema(description = "Identificador de una categoría existente", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoriaId;

    @Schema(description = "Indica si queda algún ejemplar disponible", example = "true", accessMode = Schema.AccessMode.READ_ONLY)
    public boolean isDisponible() {
        return disponibles > 0;
    }

    public void setDisponible(boolean b) {
    }
}

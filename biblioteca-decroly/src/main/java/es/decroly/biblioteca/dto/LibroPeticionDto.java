package es.decroly.biblioteca.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibroPeticionDto {
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private int ejemplares;
    private Long categoriaId;
    private int disponibles;
}

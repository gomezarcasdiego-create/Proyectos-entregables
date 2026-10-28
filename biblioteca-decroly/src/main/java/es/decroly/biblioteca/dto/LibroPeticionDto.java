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
    private String anioPublicacion;
    private int ejemplares;
    private Long categoriaId;
}

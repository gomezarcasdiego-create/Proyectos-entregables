package es.decroly.biblioteca.dto;

import lombok.Data;

@Data
public class LibroPeticionDto {
    private String titulo;
    private String autor;
    private Long categoriaId;
}

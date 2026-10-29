package es.decroly.biblioteca.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro{
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private int ejemplares;
    private int disponibles;
    private Long categoriaId;

    public boolean isDisponible() {
        return disponibles > 0;
    }

    public void setDisponible(boolean b) {
    }
}

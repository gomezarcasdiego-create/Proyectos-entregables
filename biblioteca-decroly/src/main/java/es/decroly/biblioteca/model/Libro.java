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
    private String isdn;
    private String titulo;
    private String autor;
    private Integer anioPublicacion;
    private int ejemplares;
    private int disponibles;
    private Long categoriaId;

    public boolean isDisponible() {
        return false;
    }

    public void setDisponible(boolean b) {
    }
}

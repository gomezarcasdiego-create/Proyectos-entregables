package ServiceMemoria;

import es.decroly.biblioteca.model.Categoria;
import es.decroly.biblioteca.service.CategoriaService;
import es.decroly.biblioteca.service.CategoriaServiceImpl;

import java.util.List;

public class CategoriaServiceMemoria extends CategoriaServiceImpl {
    public CategoriaServiceMemoria() {
        super(List.of(new Categoria(1L, "Novela",
                        "Libros de ficción y novelas")),

                (new Categoria(2L, "Fantasía",
                        "Libros de mundos fantásticos y magia")),

                (new Categoria(3L, "Ciencia ficción",
                        "Libros sobre tecnología y futuros imaginarios")),

                (new Categoria(4L, "Historia",
                        "Libros sobre acontecimientos históricos")),

                (new Categoria(5L, "Ciencia",
                        "Libros de conocimientos científicos")));


}
}

package es.decroly.biblioteca.service;

import es.decroly.biblioteca.model.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroServiceMemoria extends LibroServiceBase {
    public LibroServiceMemoria(CategoriaService categoriaService) {
        super(new ArrayList<>(List.of(

                (new Libro(1L, "9788408172174", "El principito",
                        "Antoine de Saint-Exupéry", 1943, 5, 5, 1L)),

                (new Libro(2L, "9788497593793", "La sombra del viento",
                        "Carlos Ruiz Zafón", 2001, 3, 3, 2L)),

                (new Libro(3L, "9788466332091", "El alquimista",
                        "Paulo Coelho", 1988, 4, 4, 3L)),

                (new Libro(4L, "9788420471839", "Cien años de soledad",
                        "Gabriel García Márquez", 1967, 2, 2, 1L)),

                (new Libro(5L, "9788498383621", "Juego de tronos",
                        "George R. R. Martin", 1996, 6, 6, 2L)))), categoriaService);

    }
}

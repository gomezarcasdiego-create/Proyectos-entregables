package es.decroly.biblioteca.service;

import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exception.BadRequestException;
import es.decroly.biblioteca.exception.ConflictException;
import es.decroly.biblioteca.exception.NotFoundException;
import es.decroly.biblioteca.model.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public abstract class LibroServiceBase implements LibroService {

    private ArrayList<Libro> libros = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(6);
    private final CategoriaService categoriaService;

    public LibroServiceBase(List<Libro> libros, CategoriaService categoriaService) {
        this.libros = new ArrayList<>(libros);
        this.categoriaService = categoriaService;
        long maxId = 0;
        for (Libro l : libros){
            if (l.getId() > maxId)
                maxId = l.getId();
        }

        this.contadorId.set(maxId + 1);
    }


    @Override
    public Libro listarLibro(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Libro buscarPorId(Long Id){
        return libros.stream()
                .filter(l -> l.getId().equals(Id))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("No se encontro con ese id" + Id));
    }

    @Override
    public List<Libro> listarLibrosDisponibles() {
        return libros.stream()
                .filter(Libro::isDisponible)
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> obtenerLibrosDeCategoria(Long id) {
        return libros.stream()
                .filter(l -> l.getCategoriaId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> listarLibrosDeAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            return new ArrayList<>(libros);
        }

        return libros.stream()
                .filter(libro -> libro.getAutor() != null
                        && libro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void validar(LibroPeticionDto dto, Long idExcluido) {
        if (dto == null) {
            throw new BadRequestException("Los datos del libro son obligatorios");
        }
        if (dto.getIsbn() == null || !dto.getIsbn().matches("[0-9]{13}")) {
            throw new BadRequestException("El ISBN debe tener exactamente 13 cifras");
        }
        if (dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            throw new BadRequestException("El título es obligatorio");
        }
        if (dto.getAutor() == null || dto.getAutor().isBlank()) {
            throw new BadRequestException("El autor es obligatorio");
        }
        if (dto.getEjemplares() < 1) {
            throw new BadRequestException("Debe haber al menos un ejemplar");
        }

        boolean isbnDuplicado = libros.stream()
                .anyMatch(actual -> !Objects.equals(actual.getId(), idExcluido)
                        && dto.getIsbn().equals(actual.getIsbn()));
        if (isbnDuplicado) {
            throw new ConflictException("Ya existe un libro con ese ISBN");
        }

        boolean categoriaExiste = dto.getCategoriaId() != null
                && categoriaService.listar().stream()
                .anyMatch(categoria -> Objects.equals(categoria.getId(), dto.getCategoriaId()));
        if (!categoriaExiste) {
            throw new BadRequestException("La categoría indicada no existe");
        }
    }

    @Override
    public Libro crear(LibroPeticionDto dto) {
        validar(dto, null);
        Libro libro = new Libro();
        libro.setId(contadorId.getAndIncrement());
        libro.setIsbn(dto.getIsbn());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        libro.setEjemplares(dto.getEjemplares());
        libro.setDisponibles(dto.getEjemplares());
        libro.setCategoriaId(dto.getCategoriaId());
        libros.add(libro);
        return libro;
    }

    @Override
    public Libro modificar(Long id, LibroPeticionDto dto) {
        Libro libro = listarLibro(id);
        if (libro == null) {
            return null;
        }
        validar(dto, id);
        libro.setIsbn(dto.getIsbn());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        libro.setEjemplares(dto.getEjemplares());
        libro.setCategoriaId(dto.getCategoriaId());
        return libro;

    }

    @Override
    public void eliminar(Long id) {
        libros.removeIf(l -> l.getId().equals(id));
    }
}



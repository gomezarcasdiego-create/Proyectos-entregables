package es.decroly.biblioteca.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Biblioteca", description = "Información general de la biblioteca")
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

    @Value("${biblioteca.nombre}")
    private String nombre;

    @Value("${biblioteca.max-prestamos}")
    private int maxPrestamos;

    @Value("${biblioteca.dias-prestamo}")
    private int diasPrestamo;

    @Operation(summary = "Consultar información de la biblioteca")
    @ApiResponse(responseCode = "200", description = "Información devuelta correctamente")
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        return ResponseEntity.ok(Map.of(
                "nombre", nombre,
                "maxPrestamos", maxPrestamos,
                "diasPrestamo", diasPrestamo
        ));
    }
}

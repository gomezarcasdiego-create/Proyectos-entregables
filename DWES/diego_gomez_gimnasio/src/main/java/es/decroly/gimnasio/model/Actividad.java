package es.decroly.gimnasio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Actividad {
    private Long  id;
    private String nombre;
    private String monitor;
    private int plazas;
    private int plazasLibres;

    public Actividad(long l, String trim, String trim1, int plazas) {
    }
}

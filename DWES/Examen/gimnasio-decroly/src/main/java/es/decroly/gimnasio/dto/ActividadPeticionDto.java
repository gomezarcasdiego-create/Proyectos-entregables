package es.decroly.gimnasio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadPeticionDto {
    private String nombre;
    private String monitor;
    private int plazas;
}

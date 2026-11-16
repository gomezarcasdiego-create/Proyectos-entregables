package es.decroly.gimnasio.controller;


import org.springframework.web.bind.annotation.GetMapping;


public class GimnasioController {
    @GetMapping("/info")
    public String info() {
        return "Examen";
    }

}

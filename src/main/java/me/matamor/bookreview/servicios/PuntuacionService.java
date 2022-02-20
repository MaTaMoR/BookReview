package me.matamor.bookreview.servicios;

import me.matamor.bookreview.modelos.Puntuacion;
import me.matamor.bookreview.repositorios.PuntuacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuntuacionService {

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    public Puntuacion insertar(Puntuacion puntuacion) {
        return this.puntuacionRepository.save(puntuacion);
    }
}

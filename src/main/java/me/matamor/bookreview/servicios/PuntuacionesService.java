package me.matamor.bookreview.servicios;

import me.matamor.bookreview.modelos.Puntuaciones;
import me.matamor.bookreview.repositorios.PuntuacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuntuacionesService {

    @Autowired
    private PuntuacionesRepository puntuacionesRepository;

    public Puntuaciones insertar(Puntuaciones puntuaciones) {
        return this.puntuacionesRepository.save(puntuaciones);
    }
}

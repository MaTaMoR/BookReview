package me.matamor.bookreview.backend.repositorios;

import me.matamor.bookreview.backend.modelos.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {

}

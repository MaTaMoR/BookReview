package me.matamor.bookreview.backend.repositorios;

import me.matamor.bookreview.backend.modelos.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {

    @Query("SELECT e FROM Editorial e WHERE LOWER(e.nombre) = LOWER(:nombre)")
    Editorial findByNombre(@Param("nombre") String nombre);

}

package me.matamor.bookreview.repositorios;

import me.matamor.bookreview.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE LOWER(c.nombre) = LOWER(:nombre)")
    Categoria findByNombre(@Param("nombre") String nombre);

}

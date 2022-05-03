package me.matamor.bookreview.backend.repositorios;

import me.matamor.bookreview.backend.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) = LOWER(:nombre)")
    Autor findByNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) = LOWER(:nombre) OR LOWER(a.apellidos) = LOWER(:apellidos)")
    Autor findByNombreOrApellidos(@Param("nombre") String nombre, @Param("apellidos") String apellidos);

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) = LOWER(:nombre) AND LOWER(a.apellidos) = LOWER(:apellidos)")
    Autor findByNombreAndApellidos(@Param("nombre") String nombre, @Param("apellidos") String apellidos);

    @Query("SELECT a FROM Autor a WHERE CONCAT(LOWER(a.nombre), ' ', LOWER(a.apellidos)) = LOWER(:fullnombre)")
    Autor findByFullNombre(@Param("fullnombre") String nombre);

}

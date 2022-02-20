package me.matamor.bookreview.repositorios;

import me.matamor.bookreview.modelos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long>, JpaSpecificationExecutor<Libro> {

    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) = LOWER(:titulo)")
    Libro findByTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.tipoLibro = :tipoLibro")
    List<Libro> findByTipoLibro(@Param("tipoLibro") Libro.TipoLibro tipoLibro);

    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> findByAutor(@Param("autor") Autor autor);

    @Query("SELECT l FROM Libro l WHERE :categoria MEMBER l.categorias")
    List<Libro> findByCategoria(@Param("categoria") Categoria categoria);

    @Query("SELECT l FROM Libro l WHERE l.editorial = :editorial")
    List<Libro> findByEditorial(@Param("editorial") Editorial editorial);

}

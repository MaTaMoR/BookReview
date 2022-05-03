package me.matamor.bookreview.backend.repositorios;

import me.matamor.bookreview.backend.modelos.Libro;
import me.matamor.bookreview.backend.modelos.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.libro = :libro")
    Review findByLibro(@Param("libro") Libro libro);
}

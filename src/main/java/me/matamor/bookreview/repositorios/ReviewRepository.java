package me.matamor.bookreview.repositorios;

import me.matamor.bookreview.modelos.Libro;
import me.matamor.bookreview.modelos.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.libro = :libro")
    Review findByLibro(@Param("libro") Libro libro);
}

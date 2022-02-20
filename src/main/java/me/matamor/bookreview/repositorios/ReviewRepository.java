package me.matamor.bookreview.repositorios;

import me.matamor.bookreview.modelos.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}

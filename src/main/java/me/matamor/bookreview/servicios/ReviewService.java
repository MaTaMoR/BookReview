package me.matamor.bookreview.servicios;

import me.matamor.bookreview.modelos.Libro;
import me.matamor.bookreview.modelos.Review;
import me.matamor.bookreview.repositorios.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review insertar(Review review) {
        return this.reviewRepository.save(review);
    }

    public Review findById(long id) {
        return this.reviewRepository.findById(id).orElse(null);
    }

    public Review findByLibro(Libro libro) {
        return this.reviewRepository.findByLibro(libro);
    }

    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    public void delete(Review review) {
        this.reviewRepository.delete(review);
    }
}

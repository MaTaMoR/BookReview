package me.matamor.bookreview.backend.controladores;

import me.matamor.bookreview.backend.modelos.Review;
import me.matamor.bookreview.backend.servicios.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Reviews");
        model.addAttribute("reviews", this.reviewService.findAll());
        return "reviews";
    }

    @GetMapping("{id}")
    public String reviews(Model model, @PathVariable("id") Long id) {
        Review review = this.reviewService.findById(id);
        if (review == null) {
            return "redirect:/reviews/";
        }

        model.addAttribute("titulo", review.getTitulo());
        model.addAttribute("review", review);

        return "review";
    }
}

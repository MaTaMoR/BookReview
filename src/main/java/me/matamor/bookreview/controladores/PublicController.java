package me.matamor.bookreview.controladores;

import me.matamor.bookreview.servicios.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PublicController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("reviews", this.reviewService.findAll());
        return "index";
    }
}

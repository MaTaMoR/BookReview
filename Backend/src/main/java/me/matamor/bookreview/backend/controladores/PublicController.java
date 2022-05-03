package me.matamor.bookreview.backend.controladores;

import me.matamor.bookreview.backend.configs.ConfiguracionServidor;
import me.matamor.bookreview.backend.servicios.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PublicController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ConfiguracionServidor configuracionServidor;

    @ModelAttribute("titulo")
    private String titulo() {
        return this.configuracionServidor.titulo;
    }

    @ModelAttribute("subTitulo")
    private String subTitulo() {
        return this.configuracionServidor.subTitulo;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("reviews", this.reviewService.findAll());
        return "index";
    }

    @GetMapping("/buscador")
    public String buscador(Model model) {
        model.addAttribute("titulo", "Buscador");
        return "buscador";
    }
}

package me.matamor.bookreview.controladores;

import me.matamor.bookreview.modelos.Libro;
import me.matamor.bookreview.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tipolibros")
public class TipoLibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tipoLibros", Libro.TipoLibro.values());
        return "tipolibros";
    }

    @GetMapping("/{tipoLibro}")
    public String libros(Model model, @PathVariable("tipoLibro") Libro.TipoLibro tipoLibro) {
        model.addAttribute("tipoLibro", tipoLibro);
        model.addAttribute("libros", this.libroService.findByTipoLibro(tipoLibro));
        return "libros";
    }
}

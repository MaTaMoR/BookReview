package me.matamor.bookreview.controladores;

import me.matamor.bookreview.busqueda.SearchMatcher;
import me.matamor.bookreview.modelos.Libro;
import me.matamor.bookreview.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/libros")
public class LibrosController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private SearchMatcher searchMatcher;

    @GetMapping("/")
    public String index(Model model, @RequestParam(name = "q", required = false) String query) {
        List<Libro> libros = null;

        if (query != null) {
            Specification<Libro> specification = this.searchMatcher.matchCriterios(query);
            if (specification == null) {
                System.out.println("No specification");
                libros = Collections.emptyList();
            } else {
                System.out.println("Yes specification");
                libros = this.libroService.findAll(specification);
            }
        }

        if (libros == null) {
            libros = this.libroService.findAll();
        }

        model.addAttribute("libros", libros);

        return "libros";
    }

    @GetMapping("/{id}")
    public String libro(Model model, @PathVariable("id") Long id) {
        Libro libro = this.libroService.findById(id);
        if (libro == null) {
            return "redirect:/libros/";
        }

        model.addAttribute("libro", libro);

        return "libro";
    }
}

package me.matamor.bookreview.controladores;

import me.matamor.bookreview.configs.ConfiguracionServidor;
import me.matamor.bookreview.modelos.Autor;
import me.matamor.bookreview.modelos.Libro;
import me.matamor.bookreview.servicios.AutorService;
import me.matamor.bookreview.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Autores");
        model.addAttribute("autores", this.autorService.findAll());
        return "autores";
    }

    @GetMapping("/{id}")
    public String autor(Model model, @PathVariable("id") Long id) {
        Autor autor = this.autorService.findById(id);
        if (autor == null) {
            return "redirect:/autores/";
        }

        List<Libro> libros = this.libroService.findByAutor(autor);

        model.addAttribute("titulo", autor.getFullNombre());
        model.addAttribute("autor", autor);
        model.addAttribute("libros", libros);

        return "libros";
    }
}

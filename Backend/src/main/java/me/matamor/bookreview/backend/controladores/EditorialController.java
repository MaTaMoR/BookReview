package me.matamor.bookreview.backend.controladores;

import me.matamor.bookreview.backend.configs.ConfiguracionServidor;
import me.matamor.bookreview.backend.modelos.Editorial;
import me.matamor.bookreview.backend.servicios.EditorialService;
import me.matamor.bookreview.backend.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private ConfiguracionServidor configuracionServidor;

    @ModelAttribute("titulo")
    private String titulo() {
        return this.configuracionServidor.titulo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Editoriales");
        model.addAttribute("editoriales", this.editorialService.findAll());
        return "editoriales";
    }

    @GetMapping("/{id}")
    public String editorial(Model model, @PathVariable("id") Long id) {
        Editorial editorial = this.editorialService.findById(id);
        if (editorial == null) {
            return "redirect:/editoriales/";
        }

        model.addAttribute("titulo", editorial.getNombre());
        model.addAttribute("editorial", editorial);
        model.addAttribute("libros", this.libroService.findByEditorial(editorial));

        return "libros";
    }
}

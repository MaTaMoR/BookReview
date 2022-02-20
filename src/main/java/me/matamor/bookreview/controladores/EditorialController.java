package me.matamor.bookreview.controladores;

import me.matamor.bookreview.modelos.Editorial;
import me.matamor.bookreview.servicios.EditorialService;
import me.matamor.bookreview.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("editoriales", this.editorialService.findAll());
        return "editoriales";
    }

    @GetMapping("/{id}")
    public String editorial(Model model, @PathVariable("id") Long id) {
        Editorial editorial = this.editorialService.findById(id);
        if (editorial == null) {
            return "redirect:/editoriales/";
        }

        model.addAttribute("editorial", editorial);
        model.addAttribute("libros", this.libroService.findByEditorial(editorial));

        return "libros";
    }
}

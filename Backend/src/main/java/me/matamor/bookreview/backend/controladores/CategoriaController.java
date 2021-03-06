package me.matamor.bookreview.backend.controladores;

import me.matamor.bookreview.backend.modelos.Categoria;
import me.matamor.bookreview.backend.servicios.CategoriaService;
import me.matamor.bookreview.backend.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Categorias");
        model.addAttribute("categorias", this.categoriaService.findAll());
        return "categorias";
    }

    @GetMapping("/{id}")
    public String categoria(Model model, @PathVariable("id") Long id) {
        Categoria categoria = this.categoriaService.findById(id);
        if (categoria == null) {
            return "redirect:/categorias";
        }

        model.addAttribute("titulo", categoria.getNombre());
        model.addAttribute("categoria", categoria);
        model.addAttribute("libros", this.libroService.findByCategoria(categoria));

        return "libros";
    }
}

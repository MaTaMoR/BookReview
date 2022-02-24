package me.matamor.bookreview.controladores;

import me.matamor.bookreview.binders.*;
import me.matamor.bookreview.modelos.*;
import me.matamor.bookreview.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PuntuacionService puntuacionService;

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Admin");
        return "/admin/index";
    }

    /*

        CATEGORIAS

     */

    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("titulo", "Categorías");
        model.addAttribute("categorias", this.categoriaService.findAll());
        return "/admin/categorias";
    }

    @GetMapping("/categoria/new")
    public String newCategoria(Model model) {
        model.addAttribute("titulo", "Nueva categoría");
        model.addAttribute("categoria", new Categoria());

        return "/admin/categoria";
    }

    @GetMapping("/categoria/edit/{id}")
    public String editCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = this.categoriaService.findById(id);
        if (categoria == null) {
            return "redirect:/admin/categorias";
        }

        model.addAttribute("titulo", "Editar categoría");
        model.addAttribute("categoria", categoria);

        return "/admin/categoria";
    }

    @GetMapping("/categoria/delete/{id}")
    public String deleteCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = this.categoriaService.findById(id);
        if (categoria == null) {
            return "redirect:/admin/categorias";
        }

        if (this.libroService.findByCategoria(categoria).size() > 0) {
            model.addAttribute("message", "No puedes borrar una categoría que tiene libros asignados!");
            model.addAttribute("titulo", "Categorías");
            model.addAttribute("categorias", this.categoriaService.findAll());
            return "/admin/categorias";
        }

        this.categoriaService.delete(categoria);

        return "redirect:/admin/categorias";
    }

    @GetMapping("/categoria/unbind/{id}")
    public String unbindCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = this.categoriaService.findById(id);
        if (categoria == null) {
            return "redirect:/admin/categorias";
        }

        model.addAttribute("titulo", "Categorías");
        model.addAttribute("categorias", this.categoriaService.findAll());

        List<Libro> libros = this.libroService.findByCategoria(categoria);
        for (Libro libro : libros) {
            libro.getCategorias().remove(categoria);
            this.libroService.insertar(libro);
        }

        if (libros.isEmpty()) {
            model.addAttribute("message", "Esta categoría no tiene ningún libro asignado!");
        } else {
            model.addAttribute("message", "Se ha desasignado la categoría de " + libros.size() + " libro(s)!");
        }

        return "/admin/categorias";
    }

    @PostMapping("/categoria/submit")
    public String categoriaSubmit(@Valid @ModelAttribute("categoria") Categoria categoria, Model model) {
        boolean nuevo = categoria.getId() == 0;

        if (nuevo) {
            if (this.categoriaService.findByNombre(categoria.getNombre()) != null) {
                model.addAttribute("message", "Ya existe una categoría con el mismo nombre!");
                return "/admin/categoria";
            }
        }

        this.categoriaService.insertar(categoria);
        return "redirect:/admin/categorias";
    }

    /*

        EDITORIALES

     */

    @GetMapping("/editoriales")
    public String editoriales(Model model) {
        model.addAttribute("titulo", "Editoriales");
        model.addAttribute("editoriales", this.editorialService.findAll());
        return "/admin/editoriales";
    }

    @GetMapping("/editorial/new")
    public String newEditorial(Model model) {
        model.addAttribute("titulo", "Nueva editorial");
        model.addAttribute("editorial", new Editorial());

        return "/admin/editorial";
    }

    @GetMapping("/editorial/edit/{id}")
    public String editEditorial(@PathVariable("id") Long id, Model model) {
        Editorial editorial = this.editorialService.findById(id);
        if (editorial == null) {
            return "redirect:/admin/editoriales";
        }

        model.addAttribute("titulo", "Editar editorial");
        model.addAttribute("editorial", editorial);

        return "/admin/editorial";
    }

    @GetMapping("/editorial/delete/{id}")
    public String deleteEditorial(@PathVariable("id") Long id, Model model) {
        Editorial editorial = this.editorialService.findById(id);
        if (editorial == null) {
            return "redirect:/admin/editoriales";
        }

        if (this.libroService.findByEditorial(editorial).size() > 0) {
            model.addAttribute("message", "No puedes borrar una editorial que tiene libros asignados!");
            model.addAttribute("titulo", "Editoriales");
            model.addAttribute("editoriales", this.categoriaService.findAll());
            return "/admin/editoriales";
        }

        this.editorialService.delete(editorial);

        return "redirect:/admin/editoriales";
    }

    @GetMapping("/editorial/unbind/{id}")
    public String unbindEditorial(@PathVariable("id") Long id, Model model) {
        Editorial editorial = this.editorialService.findById(id);
        if (editorial == null) {
            return "redirect:/admin/editoriales";
        }

        model.addAttribute("titulo", "Editoriales");
        model.addAttribute("editoriales", this.editorialService.findAll());

        List<Libro> libros = this.libroService.findByEditorial(editorial);
        for (Libro libro : libros) {
            Review review = this.reviewService.findByLibro(libro);
            if (review != null) {
                model.addAttribute("message", "Uno de los libros asignados tiene una review asignada!");
                return "/admin/editoriales";
            }
        }

        for (Libro libro : libros) {
            this.libroService.delete(libro);
        }

        if (libros.isEmpty()) {
            model.addAttribute("message", "Esta editorial no tiene ningún libro asignado!");
        } else {
            model.addAttribute("message", "Se ha desasignado la editorial de " + libros.size() + " libro(s)!");
        }

        return "/admin/editoriales";
    }

    @PostMapping("/editorial/submit")
    public String editorialSubmit(@Valid @ModelAttribute("editorial") Editorial editorial, Model model) {
        boolean nuevo = editorial.getId() == 0;

        if (nuevo) {
            if (this.editorialService.findByNombre(editorial.getNombre()) != null) {
                model.addAttribute("message", "Ya existe una editorial con el mismo nombre!");
                return "/admin/editorial";
            }
        }

        this.editorialService.insertar(editorial);
        return "redirect:/admin/editoriales";
    }

    /*

                AUTORES

     */

    @GetMapping("/autores")
    public String autores(Model model) {
        model.addAttribute("titulo", "Autores");
        model.addAttribute("autores", this.autorService.findAll());
        return "/admin/autores";
    }

    @GetMapping("/autor/new")
    public String newAutor(Model model) {
        model.addAttribute("titulo", "Nuevo autor");
        model.addAttribute("autor", new Autor());

        return "/admin/autor";
    }

    @GetMapping("/autor/edit/{id}")
    public String editAutor(@PathVariable("id") Long id, Model model) {
        Autor autor = this.autorService.findById(id);
        if (autor == null) {
            return "redirect:/admin/autores";
        }

        model.addAttribute("titulo", "Editar autor");
        model.addAttribute("autor", autor);

        return "/admin/autor";
    }

    @GetMapping("/autor/delete/{id}")
    public String deleteAutor(@PathVariable("id") Long id, Model model) {
        Autor autor = this.autorService.findById(id);
        if (autor == null) {
            return "redirect:/admin/autores";
        }

        if (this.libroService.findByAutor(autor).size() > 0) {
            model.addAttribute("message", "No puedes borrar una autor que tiene libros asignados!");
            model.addAttribute("titulo", "Autores");
            model.addAttribute("autores", this.autorService.findAll());
            return "/admin/autores";
        }

        this.autorService.delete(autor);

        return "redirect:/admin/autores";
    }

    @GetMapping("/autor/unbind/{id}")
    public String unbindAutor(@PathVariable("id") Long id, Model model) {
        Autor autor = this.autorService.findById(id);
        if (autor == null) {
            return "redirect:/admin/autores";
        }

        model.addAttribute("titulo", "Autores");
        model.addAttribute("autores", this.autorService.findAll());

        List<Libro> libros = this.libroService.findByAutor(autor);
        for (Libro libro : libros) {
            Review review = this.reviewService.findByLibro(libro);
            if (review != null) {
                model.addAttribute("message", "Uno de los libros asignados tiene una review asignada!");
                return "/admin/autores";
            }
        }

        for (Libro libro : libros) {
            this.libroService.delete(libro);
        }

        if (libros.isEmpty()) {
            model.addAttribute("message", "Este autor no tiene ningún libro asignado!");
        } else {
            model.addAttribute("message", "Se ha desasignado el autor de " + libros.size() + " libro(s)!");
        }

        return "/admin/autores";
    }

    @PostMapping("/autor/submit")
    public String autorSubmit(@Valid @ModelAttribute("autor") Autor autor, Model model) {
        boolean nuevo = autor.getId() == 0;

        if (nuevo) {
            if (this.autorService.findByNombreAndApellidos(autor.getNombre(), autor.getApellidos()) != null) {
                model.addAttribute("message", "Ya existe un autor con el mismo nombre!");
                return "/admin/autores";
            }
        }

        this.autorService.insertar(autor);
        return "redirect:/admin/autores";
    }

    /*

        LIBROS

     */

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Autor.class, new AutorPropertyEditor(this.autorService));
        webDataBinder.registerCustomEditor(Editorial.class, new EditorialPropertyEditor(this.editorialService));
        webDataBinder.registerCustomEditor(Libro.TipoLibro.class, new TipoLibroPropertyEditor());
        webDataBinder.registerCustomEditor(Date.class, new DatePropertyEditor());
    }

    @GetMapping("/libros")
    public String libros(Model model) {
        model.addAttribute("titulo", "Libros");
        model.addAttribute("libros", this.libroService.findAll());

        return "/admin/libros";
    }

    @GetMapping("/libro/edit/{id}")
    public String editLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = this.libroService.findById(id);
        if (libro == null) {
            return "redirect:/admin/libros";
        }

        model.addAttribute("titulo", "Editar libro");
        model.addAttribute("libro", libro);
        model.addAttribute("categorias", this.categoriaService.findAll());

        return "/admin/libro";
    }

    @GetMapping("/libro/new")
    public String newLibro(Model model) {
        model.addAttribute("titulo", "Nuevo libro");

        Libro libro = new Libro();
        libro.setCategorias(new ArrayList<>());

        model.addAttribute("libro", libro);
        model.addAttribute("categorias", this.categoriaService.findAll());

        return "/admin/libro";
    }

    @GetMapping("/libro/delete/{id}")
    public String deleteLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = this.libroService.findById(id);
        if (libro == null) {
            return "redirect:/admin/libros";
        }

        if (this.reviewService.findByLibro(libro) != null) {
            model.addAttribute("message", "No puedes borrar un libro que tiene una review asignada!");
            model.addAttribute("titulo", "Libros");
            model.addAttribute("libros", this.libroService.findAll());
            return "/admin/libros";
        }

        this.libroService.delete(libro);

        return "redirect:/admin/libros";
    }


    @GetMapping("/libro/unbind/{id}")
    public String unbindLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = this.libroService.findById(id);
        if (libro == null) {
            return "redirect:/admin/autores";
        }

        model.addAttribute("titulo", "Autores");
        model.addAttribute("autores", this.autorService.findAll());

        Review review = this.reviewService.findByLibro(libro);
        if (review == null) {
            model.addAttribute("message", "Este libro no tiene ninguna review!");
        } else {
            this.reviewService.delete(review);
            model.addAttribute("message", "Se ha borrado la review del libro!");
        }

        return "/admin/autores";
    }

    @PostMapping("/libro/submit")
    public String submitLibro(@Valid @ModelAttribute("libro") Libro libro, Model model) {
        boolean nuevo = libro.getId() == 0;

        if (nuevo) {
            if (this.libroService.findByTitulo(libro.getTitulo()) != null) {
                model.addAttribute("message", "Ya existe un libro con ese titulo!");
                return "/admin/libro";
            }
        }

        this.libroService.insertar(libro);

        return "redirect:/admin/libros";
    }
}

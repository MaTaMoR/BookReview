package me.matamor.bookreview.servicios;

import me.matamor.bookreview.modelos.Autor;
import me.matamor.bookreview.modelos.Categoria;
import me.matamor.bookreview.modelos.Editorial;
import me.matamor.bookreview.modelos.Libro;
import me.matamor.bookreview.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public Libro insertar(Libro libro) {
        return this.libroRepository.save(libro);
    }

    public Libro findById(long id) {
        return this.libroRepository.findById(id).orElse(null);
    }

    public List<Libro> findByTipoLibro(Libro.TipoLibro tipoLibro) {
        return this.libroRepository.findByTipoLibro(tipoLibro);
    }

    public List<Libro> findByCategoria(Categoria categoria) {
        return this.libroRepository.findByCategoria(categoria);
    }

    public List<Libro> findByEditorial(Editorial editorial) {
        return this.libroRepository.findByEditorial(editorial);
    }

    public List<Libro> findByAutor(Autor autor) {
        return this.libroRepository.findByAutor(autor);
    }

    public List<Libro> findAll() {
        return this.libroRepository.findAll();
    }

    public List<Libro> findAll(Specification<Libro> specification) {
        return this.libroRepository.findAll(specification);
    }
}

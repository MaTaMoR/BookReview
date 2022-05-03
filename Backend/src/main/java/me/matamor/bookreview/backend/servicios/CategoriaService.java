package me.matamor.bookreview.backend.servicios;

import me.matamor.bookreview.backend.modelos.Categoria;
import me.matamor.bookreview.backend.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria insertar(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria findById(long id) {
        return this.categoriaRepository.findById(id).orElse(null);
    }

    public Categoria findByNombre(String nombre) {
        return this.categoriaRepository.findByNombre(nombre);
    }

    public List<Categoria> findAll() {
        return this.categoriaRepository.findAll();
    }

    public void delete(Categoria categoria) {
        this.categoriaRepository.delete(categoria);
    }
}

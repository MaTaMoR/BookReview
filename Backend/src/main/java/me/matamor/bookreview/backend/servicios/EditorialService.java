package me.matamor.bookreview.backend.servicios;

import me.matamor.bookreview.backend.modelos.Editorial;
import me.matamor.bookreview.backend.repositorios.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public Editorial insertar(Editorial editorial) {
        return this.editorialRepository.save(editorial);
    }

    public Editorial findById(long id) {
        return this.editorialRepository.findById(id).orElse(null);
    }

    public Editorial findByNombre(String nombre) {
        return this.editorialRepository.findByNombre(nombre);
    }

    public List<Editorial> findAll() {
        return this.editorialRepository.findAll();
    }

    public void delete(Editorial editorial) {
        this.editorialRepository.delete(editorial);
    }
}

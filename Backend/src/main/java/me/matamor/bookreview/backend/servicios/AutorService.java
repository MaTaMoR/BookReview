package me.matamor.bookreview.backend.servicios;

import me.matamor.bookreview.backend.modelos.Autor;
import me.matamor.bookreview.backend.repositorios.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor insertar(Autor autor) {
        return this.autorRepository.save(autor);
    }

    public Autor findById(long id) {
        return this.autorRepository.findById(id).orElse(null);
    }

    public Autor findByNombre(String nombre) {
        return this.autorRepository.findByNombre(nombre);
    }

    public Autor findByNombreOrApellidos(String nombre, String apellidos) {
        return this.autorRepository.findByNombreOrApellidos(nombre, apellidos);
    }

    public Autor findByNombreAndApellidos(String nombre, String apellidos) {
        return this.autorRepository.findByNombreAndApellidos(nombre, apellidos);
    }

    public Autor findByFullNombre(String fullNombre) {
        return this.autorRepository.findByFullNombre(fullNombre);
    }

    public List<Autor> findAll() {
        return this.autorRepository.findAll();
    }

    public void delete(Autor autor) {
        this.autorRepository.delete(autor);
    }
}

package me.matamor.bookreview.servicios;

import me.matamor.bookreview.modelos.Autor;
import me.matamor.bookreview.repositorios.AutorRepository;
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

    public List<Autor> findAll() {
        return this.autorRepository.findAll();
    }
}

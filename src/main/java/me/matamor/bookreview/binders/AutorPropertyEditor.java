package me.matamor.bookreview.binders;

import me.matamor.bookreview.modelos.Autor;
import me.matamor.bookreview.servicios.AutorService;

import java.beans.PropertyEditorSupport;

public class AutorPropertyEditor extends PropertyEditorSupport {

    private final AutorService autorService;

    public AutorPropertyEditor(AutorService autorService) {
        this.autorService = autorService;
    }

    @Override
    public void setAsText(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            setValue(null);
        } else {
            try {
                Autor autor = this.autorService.findByFullNombre(nombre);
                if (autor == null) {
                    throw new IllegalArgumentException();
                }

                setValue(autor);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public String getAsText() {
        Autor autor = (Autor) getValue();
        return autor == null ? "" : autor.getFullNombre();
    }
}

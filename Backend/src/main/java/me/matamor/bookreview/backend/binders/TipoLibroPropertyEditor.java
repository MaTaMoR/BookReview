package me.matamor.bookreview.backend.binders;

import me.matamor.bookreview.backend.modelos.Libro;

import java.beans.PropertyEditorSupport;

public class TipoLibroPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            setValue(null);
        } else {
            Libro.TipoLibro editorial = Libro.TipoLibro.getByNombre(nombre);
            setValue(editorial);
        }
    }

    @Override
    public String getAsText() {
        Libro.TipoLibro tipoLibro = (Libro.TipoLibro) getValue();
        return tipoLibro == null ? "" : tipoLibro.getNombre();
    }
}

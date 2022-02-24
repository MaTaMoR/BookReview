package me.matamor.bookreview.binders;

import me.matamor.bookreview.modelos.Editorial;
import me.matamor.bookreview.servicios.EditorialService;

import java.beans.PropertyEditorSupport;

public class EditorialPropertyEditor extends PropertyEditorSupport {

    private final EditorialService editorialService;

    public EditorialPropertyEditor(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @Override
    public void setAsText(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            setValue(null);
        } else {
            Editorial editorial = this.editorialService.findByNombre(nombre);
            if (editorial == null) {
                throw new IllegalArgumentException();
            }

            setValue(editorial);
        }
    }

    @Override
    public String getAsText() {
        Editorial editorial = (Editorial) getValue();
        return editorial == null ? "" : editorial.getNombre();
    }
}

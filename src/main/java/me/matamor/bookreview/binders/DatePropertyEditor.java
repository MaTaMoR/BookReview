package me.matamor.bookreview.binders;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void setAsText(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            setValue(null);
        } else {
            try {
                Date date = DATE_FORMAT.parse(nombre);
                setValue(date);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public String getAsText() {
        Date fecha = (Date) getValue();
        return fecha == null ? "" : DATE_FORMAT.format(fecha);
    }
}

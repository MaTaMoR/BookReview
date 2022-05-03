package me.matamor.bookreview.backend.busqueda;

import java.util.regex.Pattern;

public enum SearchType {

    LIBRO,
    AUTOR,
    EDITORIAL,
    CATEGORIA,
    TIPOLIBRO;

    private final Pattern patron;

    SearchType() {
        patron = Pattern.compile(name().toLowerCase() + ":\"*((?<=\")[^\"]+(?=\")|[^\\s]+)\"*", Pattern.UNICODE_CHARACTER_CLASS);
    }

    public Pattern getPatron() {
        return patron;
    }
}

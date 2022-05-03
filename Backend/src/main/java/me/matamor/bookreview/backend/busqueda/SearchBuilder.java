package me.matamor.bookreview.backend.busqueda;

import me.matamor.bookreview.backend.modelos.Libro;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SearchBuilder {

    private final List<SearchCriterio> criterios;

    public SearchBuilder() {
        this.criterios = new ArrayList<>();
    }

    public SearchBuilder add(SearchCriterio searchCriterio) {
        this.criterios.add(searchCriterio);
        return this;
    }

    public Specification<Libro> build() {
        if (this.criterios.isEmpty()) {
            return null;
        }

        Specification<Libro> specification = this.criterios.get(0);

        for (int i = 1; this.criterios.size() > i; i++) {
            specification = specification.and(this.criterios.get(i));
        }

        return specification;
    }
}

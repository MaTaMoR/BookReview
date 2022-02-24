package me.matamor.bookreview.busqueda;

import me.matamor.bookreview.modelos.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class SearchCriterio implements Specification<Libro> {

    private final SearchType searchType;
    private final String value;

    public SearchCriterio(SearchType searchType, String value)  {
        this.searchType = searchType;
        this.value = value;
    }

    private String like() {
        return "%" + this.value + "%";
    }

    @Override
    public Predicate toPredicate(Root<Libro> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (this.searchType) {
            case LIBRO -> {
                return criteriaBuilder.or(criteriaBuilder.like(root.get(Libro_.TITULO), like()), criteriaBuilder.like(root.get(Libro_.DESCRIPCION), like()));
            }
            case TIPOLIBRO -> {
                Libro.TipoLibro tipoLibro = Libro.TipoLibro.getByNombre(this.value);
                if (tipoLibro == null) {
                    return criteriaBuilder.conjunction();
                }

                return criteriaBuilder.equal(root.get(Libro_.TIPO_LIBRO), tipoLibro);
            }
            case AUTOR -> {
                Join<Libro, Autor> autorJoin = root.join(Libro_.AUTOR);
                return criteriaBuilder.or(criteriaBuilder.like(autorJoin.get(Autor_.NOMBRE), like()), criteriaBuilder.like(autorJoin.get(Autor_.APELLIDOS), like()));
            }
            case EDITORIAL -> {
                Join<Libro, Editorial> editorialJoin = root.join(Libro_.EDITORIAL);
                return criteriaBuilder.like(editorialJoin.get(Editorial_.NOMBRE), like());
            }
            case CATEGORIA -> {
                Join<Libro, List<Categoria>> categoriasJoin = root.join(Libro_.CATEGORIAS);
                return criteriaBuilder.like(categoriasJoin.get(Categoria_.NOMBRE), like());
            }
            default -> {
                return criteriaBuilder.conjunction();
            }
        }
    }
}

package me.matamor.bookreview.busqueda;

import me.matamor.bookreview.modelos.Libro;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

@Component
public class SearchMatcher {

    public Specification<Libro> matchCriterios(String input) {
        SearchBuilder builder = new SearchBuilder();

        for (SearchType searchType : SearchType.values()) {
            Matcher matcher = searchType.getPatron().matcher(input);

            while (matcher.find()) {
                builder.add(new SearchCriterio(searchType, matcher.group(1)));
            }
        }

        return builder.build();
    }
}

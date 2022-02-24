package me.matamor.bookreview.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "servidor")
public class ConfiguracionServidor {

    public String titulo = "BookReview";
    public String subTitulo = "Las mejores reviews de libros";

}

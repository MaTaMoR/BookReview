package me.matamor.bookreview.backend.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "servidor")
public class ConfiguracionServidor {

    public String titulo = "Seducción entre líneas";
    public String subTitulo = "Las mejores reviews de libros";

}

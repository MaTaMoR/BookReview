package me.matamor.bookreview.backend.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Serie {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Size(min = 3, max = 128)
    private String nombre;

    public Serie() {

    }

    public Serie(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return id == serie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

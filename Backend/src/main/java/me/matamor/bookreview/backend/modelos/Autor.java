package me.matamor.bookreview.backend.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Autor {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Size(min = 3, max = 64)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 64)
    private String apellidos;

    private String imagen;

    public Autor() {

    }

    public Autor(String nombre, String apellidos) {
        this(nombre, apellidos, null);
    }

    public Autor(String nombre, String apellidos, String imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.imagen = imagen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFullNombre() {
        return this.nombre + " " + this.apellidos;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean tieneImagen() {
        return imagen != null && !imagen.isEmpty();
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}

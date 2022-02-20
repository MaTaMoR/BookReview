package me.matamor.bookreview.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Puntuacion {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @ManyToOne
    private Libro libro;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    private int puntuacion;

    public Puntuacion() {

    }

    public Puntuacion(Libro libro, Usuario usuario, int puntuacion) {
        this.libro = libro;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
    }

    public long getId() {
        return id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puntuacion that = (Puntuacion) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Puntuacion{" +
                "id=" + id +
                ", libro=" + libro +
                ", usuario=" + usuario +
                ", puntuacion=" + puntuacion +
                '}';
    }
}

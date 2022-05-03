package me.matamor.bookreview.backend.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Puntuacion {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private long puntuaciones;

    @NotNull
    private long usuario;

    @NotNull
    private int puntuacion;

    public Puntuacion() {

    }

    public Puntuacion(long puntuaciones, long usuario, int puntuacion) {
        this.puntuaciones = puntuaciones;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
    }

    public long getId() {
        return id;
    }

    public long getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(long puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
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
                ", libro=" + puntuaciones +
                ", usuario=" + usuario +
                ", puntuacion=" + puntuacion +
                '}';
    }
}

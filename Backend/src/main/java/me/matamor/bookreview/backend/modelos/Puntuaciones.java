package me.matamor.bookreview.backend.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Puntuaciones {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private long libro;

    @NotNull
    @OneToMany
    public List<Puntuacion> entries;

    public Puntuaciones() {

    }

    public Puntuaciones(long libro) {
        this.libro = libro;
        this.entries = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public long getLibro() {
        return libro;
    }

    public List<Puntuacion> getEntries() {
        return entries;
    }

    public void setEntries(List<Puntuacion> entries) {
        this.entries = entries;
    }

    public int contarPuntuaciones() {
        return this.entries.size();
    }

    public int calcularMediaPuntuaciones() {
        if (this.entries.isEmpty()) {
            return -1;
        }

        return this.entries.stream()
                .mapToInt(Puntuacion::getPuntuacion).sum() / this.entries.size();
    }
}

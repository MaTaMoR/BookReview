package me.matamor.bookreview.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Libro {

    public enum TipoLibro {

        INDEPENDIENTE,
        BILOGIA,
        TRILOGIA,
        SAGA,
        SERIE;

        private final String nombre;

        TipoLibro() {
            String rawName = name();
            this.nombre = Character.toUpperCase(rawName.charAt(0)) + rawName.substring(1).toLowerCase();
        }

        public String getNombre() {
            return this.nombre;
        }

        public static TipoLibro getByNombre(String nombre) {
            for (TipoLibro tipoLibro : values()) {
                if (tipoLibro.getNombre().equalsIgnoreCase(nombre)) {
                    return tipoLibro;
                }
            }

            return null;
        }
    }

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Size(min = 3, max = 64)
    private String titulo;

    @NotBlank
    @Size(max = 5000)
    private String descripcion;

    @NotNull
    private TipoLibro tipoLibro;

    @NotNull
    private Date fechaPublicacion;

    @NotNull
    @Min(1)
    private int numeroPaginas;

    @NotBlank
    private String imagen;

    @NotNull
    @ManyToOne
    private Autor autor;

    @NotNull
    @ManyToOne
    private Editorial editorial;

    @NotNull
    @ManyToMany
    private List<Categoria> categorias;

    @OneToOne
    private Puntuaciones puntuacion;

    public Libro() {

    }

    public Libro(String titulo, String descripcion, TipoLibro tipoLibro, Date fechaPublicacion, int numeroPaginas, String imagen, Autor autor, Editorial editorial, List<Categoria> categorias) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoLibro = tipoLibro;
        this.fechaPublicacion = fechaPublicacion;
        this.numeroPaginas = numeroPaginas;
        this.imagen = imagen;
        this.autor = autor;
        this.editorial = editorial;
        this.categorias = categorias;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoLibro getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(TipoLibro tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public boolean tienePuntuacion() {
        return this.puntuacion != null;
    }

    public Puntuaciones getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Puntuaciones puntuaciones) {
        this.puntuacion = puntuaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id == libro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipoLibro=" + tipoLibro +
                ", fechaPublicacion=" + fechaPublicacion +
                ", numeroPaginas=" + numeroPaginas +
                ", imagen='" + imagen + '\'' +
                ", autor=" + autor +
                ", editorial=" + editorial +
                ", categorias=" + categorias +
                ", puntuaciones=" + puntuacion +
                '}';
    }
}

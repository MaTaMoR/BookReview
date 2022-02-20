package me.matamor.bookreview.modelos;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Libro libro;

    @NotBlank
    private String titulo;

    @NotBlank
    @Column(length = 10000)
    private String review;

    @NotBlank
    private String imagen;

    @NotNull
    @OneToOne
    private Puntuacion puntuacion;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReview;

    public Review() {

    }

    public Review(Usuario usuario, Libro libro, String titulo, String review, String imagen, Puntuacion puntuacion) {
        this.usuario = usuario;
        this.libro = libro;
        this.titulo = titulo;
        this.review = review;
        this.imagen = imagen;
        this.puntuacion = puntuacion;
    }

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Puntuacion getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Puntuacion puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(Date fechaReview) {
        this.fechaReview = fechaReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;
        return id == review.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", libro=" + libro +
                ", titulo='" + titulo + '\'' +
                ", review='" + review + '\'' +
                ", imagen='" + imagen + '\'' +
                ", puntuacion=" + puntuacion +
                ", fechaReview=" + fechaReview +
                '}';
    }
}

package me.matamor.bookreview.modelos;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 3, max = 16)
    private String username;

    @NotBlank
    @Size(min = 3, max = 16)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 32)
    private String apellidos;

    private String avatar;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotBlank
    private String password;

    public Usuario() {

    }

    public Usuario(String username, String nombre, String apellidos, String avatar, String email, String password) {
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", avatar='" + avatar + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
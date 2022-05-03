package me.matamor.bookreview.backend.servicios;

import me.matamor.bookreview.backend.modelos.Usuario;
import me.matamor.bookreview.backend.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
        return this.usuarioRepository.save(usuario);
    }

    public Usuario findByUsername(String email) {
        return this.usuarioRepository.findFirstByUsername(email);
    }

    public Usuario findByEmail(String email) {
        return this.usuarioRepository.findFirstByEmail(email);
    }

    public Usuario findByUsernameOrEmail(String username, String email) {
        return this.usuarioRepository.findByUsernameOrEmail(username, email);
    }
}

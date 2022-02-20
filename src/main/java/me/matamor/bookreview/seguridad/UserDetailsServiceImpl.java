package me.matamor.bookreview.seguridad;

import me.matamor.bookreview.modelos.Usuario;
import me.matamor.bookreview.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsernameOrEmail(username, username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        } else {
            User.UserBuilder userBuilder = User
                    .withUsername(usuario.getUsername())
                    .disabled(false)
                    .password(usuario.getPassword())
                    .authorities(new SimpleGrantedAuthority("ROLE_USER"));

            return userBuilder.build();
        }
    }
}

package me.matamor.bookreview.controladores;

import me.matamor.bookreview.configs.ConfiguracionServidor;
import me.matamor.bookreview.modelos.Usuario;
import me.matamor.bookreview.servicios.UsuarioService;
import me.matamor.bookreview.upload.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("titulo", "Login");
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("titulo", "Register");
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register/submit")
    public String postRegister(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
        Usuario creado = this.usuarioService.findByUsernameOrEmail(usuario.getUsername(), usuario.getEmail());
        if (creado != null) {
            model.addAttribute("error", "Ya existe un usuario con ese username o corre electrónico!");
            return "register";
        }

        this.usuarioService.registrar(usuario);

        return "redirect:/auth/login";
    }
}

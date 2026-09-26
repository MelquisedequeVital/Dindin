package br.edu.ifpb.pweb2.dindin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dindin.model.Usuario;
import br.edu.ifpb.pweb2.dindin.services.AuthService;
import br.edu.ifpb.pweb2.dindin.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    public AuthService authService;

    @Autowired
    public UsuarioService usuarioService;

    @GetMapping("/login")
    public String getLoginForm() {
        return "auth/form";
    }

    @PostMapping("/login")
    public String login(String username,
                        String senha, 
                        HttpServletRequest httpServletRequest, 
                        RedirectAttributes attr) {

        Optional<Usuario> usuarioAutenticado = authService.autenticarUsuario(username, senha);

        if (usuarioAutenticado.isPresent()) {
            HttpSession oldSession = httpServletRequest.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("usuarioLogado", usuarioAutenticado.get());
            return "redirect:/contas";
        }

        attr.addFlashAttribute("mensagemCredenciaisInvalidas", "Credenciais Inválidas");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, RedirectAttributes attr) {
        HttpSession session = httpServletRequest.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        attr.addAttribute("mensagemLogout", "Você deslogou com sucesso");
        return "redirect:/";
    }
}

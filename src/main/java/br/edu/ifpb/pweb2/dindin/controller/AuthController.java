package br.edu.ifpb.pweb2.dindin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dindin.model.Correntista;
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

        Optional<Correntista> usuarioAutenticado = authService.autenticarUsuario(username, senha);

        if (usuarioAutenticado.isPresent()) {
            HttpSession oldSession = httpServletRequest.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("usuarioLogado", usuarioAutenticado.get());
            if(usuarioAutenticado.get().isAdmin()){
                return "redirect:/admin/correntistas";
            }
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

        attr.addFlashAttribute("mensagemLogout", "Você deslogou com sucesso");
        return "redirect:/";
    }

    @GetMapping("/cadastro")
    public String getCadastroForm(Correntista usuario){
        return "auth/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(Correntista usuario, RedirectAttributes redirectAttributes){
        if(usuarioService.usuarioJaExiste(usuario.getUsername())) {
            redirectAttributes.addFlashAttribute("mensagemErro","Esse email já está em uso.");
            return "redirect:cadastro";
        }

        usuarioService.cadastrar(usuario);
        redirectAttributes.addFlashAttribute("mensagemSucesso","Cadastro realizado com sucesso!");
        return "redirect:/login";
    }
}

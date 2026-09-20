package br.edu.ifpb.pweb2.dindin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dindin.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;

@Controller 
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public String getLoginForm(){
        return "auth/form";
    }

    @PostMapping("/login")
    public String login(String email, String senha, HttpServletRequest httpServletRequest, RedirectAttributes attr){
        
        if(!authService.credenciaisDeUsuarioValidas(email, senha)){
            attr.addFlashAttribute("mensagemCredenciaisInvalidas", "Credenciais Inválidas");
            return "redirect:/login";
        }

        return "redirect:home";

    }
}

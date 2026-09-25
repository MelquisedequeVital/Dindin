package br.edu.ifpb.pweb2.dindin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    //TODO: retirar o catalogo daqui(Fazer um Home Controller talvez)
    @GetMapping("/catalogo")
    public String getHome(HttpSession httpSession, Model model){
        String sessionId = httpSession.getId();
        model.addAttribute("sessionId", sessionId);
        return "catalogo";
    }
}

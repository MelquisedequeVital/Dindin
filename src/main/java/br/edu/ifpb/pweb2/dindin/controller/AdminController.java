package br.edu.ifpb.pweb2.dindin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dindin.model.Usuario;
import br.edu.ifpb.pweb2.dindin.model.enums.Role;
import br.edu.ifpb.pweb2.dindin.services.UsuarioService;

@Controller 
@RequestMapping("/admin") 
public class AdminController {

    @Autowired 
    private UsuarioService usuarioService; 

    @GetMapping("correntista/lista")
    public String listaCorrentista(Model model){
        model.addAttribute("correntistas", usuarioService.findAll());
        return "admin/correntista/lista";
    }

    @GetMapping("/correntista/form")
    public String formNovoCorrentista(Model model){
        model.addAttribute("correntista", new Usuario());
        model.addAttribute("roles", List.of(Role.values()));
        return "admin/correntista/form";
    }

    @PostMapping("/correntista/adicionar")
    public String adicionarCorrentista(Usuario usuario, RedirectAttributes attr){

        if(usuarioService.usernameJaExiste(usuario.getUsername())){
            attr.addFlashAttribute("mensagemUsernameInvalido", "Esse username já existe, pense em outro");
            return "redirect:form";
        }
        
        usuarioService.save(usuario);
        return "redirect:lista";
    }
}

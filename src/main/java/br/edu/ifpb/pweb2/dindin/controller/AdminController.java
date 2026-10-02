package br.edu.ifpb.pweb2.dindin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Correntista;
import br.edu.ifpb.pweb2.dindin.model.enums.Role;
import br.edu.ifpb.pweb2.dindin.services.ContaService;
import br.edu.ifpb.pweb2.dindin.services.UsuarioService;

@Controller 
@RequestMapping("/admin") 
public class AdminController {

    @Autowired 
    private UsuarioService usuarioService; 

    @Autowired 
    private ContaService contaService;

    @GetMapping("/correntistas")
    public String listaCorrentista(Model model){
        model.addAttribute("correntistas", usuarioService.findAll());
        return "admin/correntistas/lista";
    }

    @GetMapping("/correntistas/novo")
    public String formNovoCorrentista(Model model){
        model.addAttribute("correntista", new Correntista());
        model.addAttribute("roles", List.of(Role.values()));
        return "admin/correntistas/form";
    }

    @PostMapping("/correntistas")
    public String adicionarCorrentista(Correntista usuario, RedirectAttributes attr){

        if(usuarioService.usuarioJaExiste(usuario.getUsername())){
            attr.addFlashAttribute("mensagemUsernameInvalido", "Esse username já existe, pense em outro");
            return "redirect:/admin/correntistas/novo";
        }
        
        usuarioService.save(usuario);
        return "redirect:/admin/correntistas";
    }

    @GetMapping("correntista/{id}/contas")
    public String acessarContadeCorrentista(@PathVariable("id") Long idCorrentista ,Model model){
        Correntista usuarioVisualizado = usuarioService.findById(idCorrentista);
        List<Conta> listaContas = contaService.findByCorrentistaId(idCorrentista);
        model.addAttribute("contas", listaContas);
        model.addAttribute("usuarioSendoVisualizado", usuarioVisualizado);

        return "contas/lista";
    }



    
}

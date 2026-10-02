package br.edu.ifpb.pweb2.dindin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Correntista;
import br.edu.ifpb.pweb2.dindin.services.AuthService;
import br.edu.ifpb.pweb2.dindin.services.ContaService;
import br.edu.ifpb.pweb2.dindin.services.TransacaoService;
import br.edu.ifpb.pweb2.dindin.services.UsuarioService;
import jakarta.servlet.http.HttpSession;


@Controller
public class CorrentistaController {

}

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
import br.edu.ifpb.pweb2.dindin.model.Usuario;
import br.edu.ifpb.pweb2.dindin.services.ContaService;
import br.edu.ifpb.pweb2.dindin.services.TransacaoService;
import br.edu.ifpb.pweb2.dindin.services.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContaService contaService;

    @Autowired 
    private TransacaoService transacaoService;

    @GetMapping("/contas")
    public String listaContas(HttpSession session, Model model) {
        Usuario correntista = (Usuario) session.getAttribute("usuarioLogado");
        List<Conta> listaContas = contaService.findByCorrentista(correntista);
        model.addAttribute("contas", listaContas);
        return "contas/lista";
    }

    @GetMapping("/contas/nova")
    public String contaForm(Model model) {
        model.addAttribute("conta", new Conta());
        return "contas/form";
    }

    //Rota pra exibir o extrato da conta ne
    @GetMapping("/contas/{id}")
    public String extratoConta(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<Conta> optConta = contaService.findById(id);
        if (optConta.isEmpty()){
            redirectAttributes.addFlashAttribute("mensagemErro","Conta não encontrada!");
            return "redirect:/contas";
        }

        Conta conta = optConta.get();
        model.addAttribute("conta",conta);
        model.addAttribute("transacoes",transacaoService.findByConta(conta));
        return "contas/extrato";
    }



    @PostMapping("/contas")
    public String addConta(HttpSession session, Conta conta, RedirectAttributes attr){
        
        if(contaService.contaJaExiste(conta.getNumero())){
            attr.addFlashAttribute("mensagemContaInvalida", "Conta existente, digite outro número");
            return "redirect:/contas/nova";
        }

        if(conta.getNumero() == null || conta.getNumero().trim().isEmpty()){
            attr.addFlashAttribute("mensagemContaInvalida", "Número da conta obrigatório");
            return "redirect:/contas/nova";
        }
        
        Usuario correntista = (Usuario) session.getAttribute("usuarioLogado");
        conta.setCorrentista(correntista);
        contaService.salvar(conta);
        return "redirect:/contas";

    }

    @PostMapping("contas/{id}/deletar")
    public String deletarConta(@PathVariable("id") Long idConta){
        contaService.deleteById(idConta);
        return "redirect:/contas";
    }

}

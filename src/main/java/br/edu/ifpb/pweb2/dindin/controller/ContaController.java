package br.edu.ifpb.pweb2.dindin.controller;

import java.util.List;
import java.util.Optional;

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
import br.edu.ifpb.pweb2.dindin.services.AuthService;
import br.edu.ifpb.pweb2.dindin.services.ContaService;
import br.edu.ifpb.pweb2.dindin.services.TransacaoService;
import jakarta.servlet.http.HttpSession;

@Controller 
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public String listaContas(HttpSession session, Model model) {
        Correntista correntista = authService.getUsuarioLogado(session);
        List<Conta> listaContas = contaService.findByCorrentista(correntista);
        model.addAttribute("contas", listaContas);
        model.addAttribute("nomeCorrentistaLogado", correntista.getNome());
        return "contas/lista";
    }

    @GetMapping("/nova")
    public String contaForm(HttpSession session, Model model) {
        Correntista correntista = authService.getUsuarioLogado(session);
        model.addAttribute("conta", new Conta());
        model.addAttribute("nomeCorrentistaLogado", correntista.getNome());
        model.addAttribute("isAdmin", correntista.isAdmin());
        return "contas/form";
    }

    @GetMapping("/{id}")
    public String extratoConta(@PathVariable("id") Long id, Model model, HttpSession session,
            RedirectAttributes redirectAttributes) {
        Optional<Conta> optConta = contaService.findById(id);
        Correntista usuarioLogado = authService.getUsuarioLogado(session);
        if (optConta.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Conta não encontrada!");
            return "redirect:/contas";
        }

        Conta conta = optConta.get();
        model.addAttribute("conta", conta);
        model.addAttribute("nomeCorrentistaLogado", usuarioLogado.getNome());
        model.addAttribute("isAdmin", usuarioLogado.isAdmin());
        model.addAttribute("valorTotalGasto", optConta.get().calcularSaldo());
        model.addAttribute("transacoes", transacaoService.findByConta(conta));
        model.addAttribute("valorInvestido", optConta.get().calcularTotalInvestido());
        return "contas/extrato";
    }

    @PostMapping
    public String addConta(HttpSession session, Conta conta, RedirectAttributes attr) {
        if (contaService.contaJaExiste(conta.getNumero())) {
            attr.addFlashAttribute("mensagemContaInvalida", "Conta existente, digite outro número");
            return "redirect:/contas/nova";
        }

        if (conta.getNumero() == null || conta.getNumero().trim().isEmpty()) {
            attr.addFlashAttribute("mensagemContaInvalida", "Número da conta obrigatório");
            return "redirect:/contas/nova";
        }

        Correntista correntista = (Correntista) session.getAttribute("usuarioLogado");
        conta.setCorrentista(correntista);
        contaService.salvar(conta);
        return "redirect:/contas";
    }

    @PostMapping("/{id}/deletar")
    public String deletarConta(@PathVariable("id") Long idConta) {
        contaService.deleteById(idConta);
        return "redirect:/contas";
    }
}
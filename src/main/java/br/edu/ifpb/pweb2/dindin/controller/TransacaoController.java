package br.edu.ifpb.pweb2.dindin.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Transacao;
import br.edu.ifpb.pweb2.dindin.services.CategoriaService;
import br.edu.ifpb.pweb2.dindin.services.ContaService;
import br.edu.ifpb.pweb2.dindin.services.TransacaoService;

@Controller 
@RequestMapping("/transacoes")
public class TransacaoController {
    
    private final TransacaoService transacaoService;
    private final ContaService contaService;
    private final CategoriaService categoriaService;

    public TransacaoController(TransacaoService transacaoService, ContaService contaService, CategoriaService categoriaService){
        this.transacaoService = transacaoService;
        this.contaService = contaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/nova/{contaId}")
    public String formNovaTransacao(@PathVariable("contaId") Long contaId, Model model, RedirectAttributes redirectAttributes){
        Optional<Conta> optConta = contaService.findById(contaId);
        if (optConta.isEmpty()){
            redirectAttributes.addFlashAttribute("mensagemErro","Conta não encontrada");
            return "redirect:/contas";
        }
        Transacao transacao = new Transacao();
        transacao.setConta(optConta.get());

        model.addAttribute("transacao", transacao);
        model.addAttribute("categorias", categoriaService.findAllAtivas());
        return "transacoes/form";
    }

// TODO: data não está aindo automaticamente
    @GetMapping("/editar/{id}")
    public String formEditarTransacao(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<Transacao> optTransacao = transacaoService.findById(id);
        if (optTransacao.isEmpty()){
            redirectAttributes.addFlashAttribute("mensagemErro","Transação não encontrada.");
            return "redirect:/contas";
        }

        model.addAttribute("transacao",optTransacao.get());
        model.addAttribute("categorias",categoriaService.findAllAtivas());
        return "transacoes/form";
    }

    @PostMapping ("/salvar")
    public String salvarTransacao(@ModelAttribute("transacao") Transacao transacao, RedirectAttributes redirectAttributes){
        transacaoService.salvar(transacao);
        redirectAttributes.addFlashAttribute("mensagemSucesso","Transação salva com sucesso!");
        
        if (transacao.getConta() != null && transacao.getConta().getId() != null){
            return "redirect:/contas/" + transacao.getConta().getId();
        }

        return "redirect:/contas";
    }
}

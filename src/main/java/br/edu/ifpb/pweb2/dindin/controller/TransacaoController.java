package br.edu.ifpb.pweb2.dindin.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import br.edu.ifpb.pweb2.dindin.model.Comentario;
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
    public String salvarTransacao(@ModelAttribute("transacao") Transacao transacao, 
    @RequestParam(value= "novoComentario", required = false) String novoComentario, RedirectAttributes redirectAttributes){
       
        if (transacao.getComentarios() != null){
            for (Comentario c : transacao.getComentarios()){
                c.setTransacao(transacao);
            }
        }

        if (novoComentario != null && !novoComentario.isBlank()){
            Comentario comentario = new Comentario();
            comentario.setTexto(novoComentario);
            transacao.addComentario(comentario);
        }
        
        transacaoService.salvar(transacao);
        redirectAttributes.addFlashAttribute("mensagemSucesso","Transação salva com sucesso!");
        
        if (transacao.getConta() != null && transacao.getConta().getId() != null){
            return "redirect:/contas/" + transacao.getConta().getId();
        }

        return "redirect:/contas";
    }

    @PostMapping("/remover/{id}")
    public String removerTransacao(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Optional<Transacao> optTransacao = transacaoService.findById(id);
        if (optTransacao.isPresent()){
            Transacao transacao = optTransacao.get();
            Long contaId = transacao.getConta() != null ? transacao.getConta().getId() : null;

            transacaoService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso","Transação apagada com sucesso!");

            if (contaId != null){
                return "redirect:/contas/"+ contaId;
            }
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro","Transação não encontrada!");
        }
        return "redirect:/contas";
    }
}

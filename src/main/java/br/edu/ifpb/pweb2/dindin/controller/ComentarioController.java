package br.edu.ifpb.pweb2.dindin.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dindin.model.Comentario;
import br.edu.ifpb.pweb2.dindin.model.Transacao;
import br.edu.ifpb.pweb2.dindin.services.ComentarioService;
import br.edu.ifpb.pweb2.dindin.services.TransacaoService;

@Controller 
@RequestMapping("/comentarios")
public class ComentarioController {
    private final ComentarioService comentarioService;
    private final TransacaoService transacaoService;

    public ComentarioController(ComentarioService comentarioService, TransacaoService transacaoService){
        this.comentarioService = comentarioService;
        this.transacaoService = transacaoService;
    }

    @PostMapping("/salvar")
    public String salvarComentario(@RequestParam("transacaoId") Long transacaoId, @RequestParam("contaId") Long contaId, @RequestParam("texto") String texto, RedirectAttributes redirectAttributes){
        Optional<Transacao> optTransacao = transacaoService.findById(transacaoId);
        
        if (optTransacao.isPresent()){
            Transacao transacao = optTransacao.get();
            Comentario comentario = transacao.getComentario();

            if(comentario == null){
                comentario = new Comentario();
            }

            comentario.setTexto(texto);
            comentarioService.salvarOuAtualizar(comentario, transacao);
            redirectAttributes.addFlashAttribute("mensagemSucesso","Comentário salvo com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Transação não encontrada.");
        }
        return "redirect:/contas/" + contaId;
    }

    @PostMapping("/remover/{transacaoId}")
    public String removerComentario(@PathVariable("transacaoId") Long transacaoId, @RequestParam("contaId") Long contaId, RedirectAttributes redirectAttributes){
        Optional<Transacao> optTransacao = transacaoService.findById(transacaoId);

        if(optTransacao.isPresent()){
            Transacao transacao = optTransacao.get();
            if (transacao.getComentario() != null){
                Long comentarioId = transacao.getComentario().getId();
                comentarioService.excluir(comentarioId, transacao);
                redirectAttributes.addFlashAttribute("mensagemSucesso","Comentário removido com sucesso!");
            }

        } else {
            redirectAttributes.addFlashAttribute("mensagemErro","Transação não encontrada");
        }

        return "redirect:/contas/" + contaId;
    }
}

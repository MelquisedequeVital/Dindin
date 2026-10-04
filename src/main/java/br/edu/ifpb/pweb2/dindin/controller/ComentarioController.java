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
        
        if (optTransacao.isPresent() && texto!= null && !texto.isBlank()){
            Transacao transacao = optTransacao.get();

            Comentario novoComentario = new Comentario();
            novoComentario.setTexto(texto);
            comentarioService.salvarOuAtualizar(novoComentario, transacao);
            redirectAttributes.addFlashAttribute("mensagemSucesso","Comentario adicionado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Transação não encontrada.");
        }
        return "redirect:/contas/" + contaId;
    }

    @PostMapping("/remover")
    public String removerComentario(@RequestParam(value = "comentarioId", required = false) Long comentarioId, @RequestParam("contaId") Long contaId, RedirectAttributes redirectAttributes){
        if (comentarioId != null){
            try{
                comentarioService.excluir(comentarioId);
                redirectAttributes.addFlashAttribute("mensagemSucesso","Comentário removido com sucesso!");
            } catch(Exception e){
                redirectAttributes.addFlashAttribute("mensagemErro","Erro ao remover o comentário.");
            }
        } else{
            redirectAttributes.addFlashAttribute("mensagemErro", "Identificador do comentário inválido.");     
        }

        return "redirect:/contas/" + contaId;
    }
}

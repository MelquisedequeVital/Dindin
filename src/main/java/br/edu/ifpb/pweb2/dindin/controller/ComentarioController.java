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
        // pra verificar se está em branco
        if (texto == null || texto.isBlank()){
            redirectAttributes.addFlashAttribute("mensagemErro","O comentário não pode ser vazio.");
            return "redirect:/contas/" + contaId;
        }
        // pra verificar se transação existe mesmo
        Optional<Transacao> optTransacao = transacaoService.findById(transacaoId);
        if (optTransacao.isEmpty()){
            redirectAttributes.addFlashAttribute("mensagemErro","Transação não encontrada.");
            return "redirect:/contas/" + contaId;
        }
        // set no comentario, o trim é pra tirar espaço em branco
        Comentario novoComentario = new Comentario();
        novoComentario.setTexto(texto.trim());
        comentarioService.salvarOuAtualizar(novoComentario, optTransacao.get());
        redirectAttributes.addFlashAttribute("mensagemSucesso","Comentario adicionado com sucesso!");
        return "redirect:/contas/" + contaId;    
    }

    @PostMapping("/remover")
    public String removerComentario(@RequestParam Long comentarioId, @RequestParam("contaId") Long contaId, RedirectAttributes redirectAttributes){
        try{
            comentarioService.excluir(comentarioId);
            redirectAttributes.addFlashAttribute("mensagemSucesso","Comentário removido com sucesso!");
        } catch(Exception e){
            redirectAttributes.addFlashAttribute("mensagemErro","Erro ao remover o comentário.");
        }
        return "redirect:/contas/" + contaId;
    }
}

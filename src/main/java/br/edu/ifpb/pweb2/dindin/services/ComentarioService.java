package br.edu.ifpb.pweb2.dindin.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Comentario;
import br.edu.ifpb.pweb2.dindin.model.Transacao;
import br.edu.ifpb.pweb2.dindin.repository.ComentarioRepository;
import br.edu.ifpb.pweb2.dindin.repository.TransacaoRepository;

@Service 
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final TransacaoRepository transacaoRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, TransacaoRepository transacaoRepository){
        this.comentarioRepository = comentarioRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Optional<Comentario> findById(Long id){
        return comentarioRepository.findById(id);
    }

    public Comentario salvarOuAtualizar(Comentario comentario, Transacao transacao){
        comentario.setTransacao(transacao);
        Comentario salvo = comentarioRepository.save(comentario);

        transacao.setComentario(salvo);
        transacaoRepository.save(transacao);

        return salvo;
    }
    // excluir comentario
    public void excluir(Long comentarioId, Transacao transacao){
        transacao.setComentario(null);
        transacaoRepository.save(transacao);
        comentarioRepository.deleteById(comentarioId);
    }
}

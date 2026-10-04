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

    public ComentarioService(ComentarioRepository comentarioRepository){
        this.comentarioRepository = comentarioRepository;
    }

    public Optional<Comentario> findById(Long id){
        return comentarioRepository.findById(id);
    }

    public Comentario salvarOuAtualizar(Comentario comentario, Transacao transacao){
        comentario.setTransacao(transacao);
        if(transacao.getComentarios() != null){
            transacao.addComentario(comentario);
        }
        return comentarioRepository.save(comentario);
    }
    public void excluir(Long comentarioId){
        comentarioRepository.deleteById(comentarioId);
    }
}

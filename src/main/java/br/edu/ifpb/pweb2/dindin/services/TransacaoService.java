package br.edu.ifpb.pweb2.dindin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Transacao;
import br.edu.ifpb.pweb2.dindin.repository.TransacaoRepository;

@Service 
public class TransacaoService {
   
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository){
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao salvar(Transacao transacao){
        return transacaoRepository.save(transacao);
    }

    public Optional<Transacao> findById(Long id){
        return transacaoRepository.findById(id);
    }

    public List<Transacao> findByConta(Conta conta) {
        return transacaoRepository.findByConta(conta);

    }
 
    public void excluir(Long id) {
        transacaoRepository.deleteById(id);
    }

    
}

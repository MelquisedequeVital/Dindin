package br.edu.ifpb.pweb2.dindin.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
    List<Transacao> findByConta(Conta conta);
}

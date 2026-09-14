package br.edu.ifpb.pweb2.dindin.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}

package br.edu.ifpb.pweb2.dindin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Correntista;


public interface ContaRepository extends JpaRepository<Conta, Long>{

    List<Conta> findByCorrentista(Correntista correntista);

    List<Conta> findByCorrentistaId(Long id);

    Optional<Conta> findByNumero(String numero);

    Optional<Conta> findByNumeroAndCorrentistaId(String numero, Long correntistaId);
}

package br.edu.ifpb.pweb2.dindin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Usuario;


public interface ContaRepository extends JpaRepository<Conta, Long>{

    List<Conta> findByCorrentista(Usuario correntista);

    Optional<Conta> findByNumero(Integer numero);
}

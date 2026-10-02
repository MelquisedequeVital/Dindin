package br.edu.ifpb.pweb2.dindin.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Correntista;


public interface UsuarioRepository extends JpaRepository<Correntista, Long> {
    public Optional<Correntista> findByUsername(String username);
}

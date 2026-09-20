package br.edu.ifpb.pweb2.dindin.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //TODO: Verificar se a falta de um Optional aqui da erro
    public Optional<Usuario> findByUsername(String username);
}

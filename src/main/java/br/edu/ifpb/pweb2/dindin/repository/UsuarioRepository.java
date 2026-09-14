package br.edu.ifpb.pweb2.dindin.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

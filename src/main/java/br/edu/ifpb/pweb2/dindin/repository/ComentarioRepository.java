package br.edu.ifpb.pweb2.dindin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    
}

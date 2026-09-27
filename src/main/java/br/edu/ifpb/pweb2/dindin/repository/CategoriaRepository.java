package br.edu.ifpb.pweb2.dindin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.dindin.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    List<Categoria> findByAtivoTrue();
}

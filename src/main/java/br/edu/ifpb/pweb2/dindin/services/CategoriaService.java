package br.edu.ifpb.pweb2.dindin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Categoria;
import br.edu.ifpb.pweb2.dindin.repository.CategoriaRepository;

@Service 
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAllAtivas(){
        return categoriaRepository.findByAtivoTrue();
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}

package br.edu.ifpb.pweb2.dindin.services;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Usuario;
import br.edu.ifpb.pweb2.dindin.repository.UsuarioRepository;


@Service
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    public Usuario findByUsername(String username){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByUsername(username);
        return usuarioEncontrado.get();
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public boolean usuarioJaExiste(String username){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByUsername(username);
        
        return usuarioEncontrado.isPresent();
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).get();
    }

}

package br.edu.ifpb.pweb2.dindin.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

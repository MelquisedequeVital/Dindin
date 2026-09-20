package br.edu.ifpb.pweb2.dindin.services;

import br.edu.ifpb.pweb2.dindin.repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Usuario;

@Service 
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean credenciaisDeUsuarioValidas(String username, String senha){
        Optional<Usuario> usuarioEncnontrado = usuarioRepository.findByUsername(username);
        if(usuarioEncnontrado.isEmpty()){
            return false;
        }
        return usuarioEncnontrado.get().getSenha().equals(senha);
    }
}

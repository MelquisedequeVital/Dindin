package br.edu.ifpb.pweb2.dindin.services;

import br.edu.ifpb.pweb2.dindin.repository.UsuarioRepository;
import br.edu.ifpb.pweb2.dindin.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Usuario;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> autenticarUsuario(String username, String senha) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByUsername(username);
        if (usuarioEncontrado.isPresent() && PasswordUtil.checkPass(senha, usuarioEncontrado.get().getSenha())) {
            return usuarioEncontrado;
        }

        return Optional.empty();

    }

    public Usuario getUsuarioLogado(HttpSession session){
        return (Usuario) session.getAttribute("usuarioLogado");
    }
}

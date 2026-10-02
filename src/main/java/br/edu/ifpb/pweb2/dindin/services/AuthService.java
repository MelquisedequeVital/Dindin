package br.edu.ifpb.pweb2.dindin.services;

import br.edu.ifpb.pweb2.dindin.repository.UsuarioRepository;
import br.edu.ifpb.pweb2.dindin.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Correntista;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Correntista> autenticarUsuario(String username, String senha) {
        Optional<Correntista> usuarioEncontrado = usuarioRepository.findByUsername(username);
        if (usuarioEncontrado.isPresent() && PasswordUtil.checkPass(senha, usuarioEncontrado.get().getSenha())) {
            return usuarioEncontrado;
        }

        return Optional.empty();

    }

    public Correntista getUsuarioLogado(HttpSession session){
        return (Correntista) session.getAttribute("usuarioLogado");
    }
}

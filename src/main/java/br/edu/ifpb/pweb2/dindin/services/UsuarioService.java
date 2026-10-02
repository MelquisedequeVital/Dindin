package br.edu.ifpb.pweb2.dindin.services;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Correntista;
import br.edu.ifpb.pweb2.dindin.model.enums.Role;
import br.edu.ifpb.pweb2.dindin.repository.UsuarioRepository;
import br.edu.ifpb.pweb2.dindin.util.PasswordUtil;


@Service
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    public Correntista findByUsername(String username){
        Optional<Correntista> usuarioEncontrado = usuarioRepository.findByUsername(username);
        return usuarioEncontrado.get();
    }

    public List<Correntista> findAll(){
        return usuarioRepository.findAll();
    }

    public void save(Correntista usuario){
        usuarioRepository.save(usuario);
    }

    public boolean usuarioJaExiste(String username){
        Optional<Correntista> usuarioEncontrado = usuarioRepository.findByUsername(username);
        
        return usuarioEncontrado.isPresent();
    }

    public Correntista findById(Long id){
        return usuarioRepository.findById(id).get();
    }

    public void cadastrar(Correntista usuario){
        String senhaHash = PasswordUtil.hashPassword(usuario.getSenha());
        usuario.setSenha(senhaHash);
        usuario.setBloqueado(false);
        usuario.setRole(Role.ROLE_CORRENTISTA);

        usuarioRepository.save(usuario);
    }

}

package br.edu.ifpb.pweb2.dindin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Usuario;
import br.edu.ifpb.pweb2.dindin.repository.ContaRepository;

@Service 
public class ContaService {

    @Autowired 
    private ContaRepository contaRepository;

    public List<Conta> findByCorrentista(Usuario correntista){
        return contaRepository.findByCorrentista(correntista);
    }

    public void salvar(Conta conta){
        contaRepository.save(conta);
    }

    public boolean contaJaExiste(Integer numero){
        Optional<Conta> contaEncontrada = contaRepository.findByNumero(numero);

        return contaEncontrada.isPresent();
    }

}

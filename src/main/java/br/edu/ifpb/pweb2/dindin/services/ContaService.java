package br.edu.ifpb.pweb2.dindin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.dindin.mapper.ContaMapper;
import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Correntista;
import br.edu.ifpb.pweb2.dindin.model.dtos.ContaForm;
import br.edu.ifpb.pweb2.dindin.repository.ContaRepository;

@Service 
public class ContaService {

    @Autowired 
    private ContaRepository contaRepository;

    @Autowired 
    private ContaMapper contaMapper;

    public List<Conta> findByCorrentista(Correntista correntista){
        return contaRepository.findByCorrentista(correntista);
    }

    public Optional<Conta> findById(Long id){
        return contaRepository.findById(id);
    }

    public void salvar(Conta conta){
        contaRepository.save(conta);
    }

    public boolean contaJaExiste(String numero){
        Optional<Conta> contaEncontrada = contaRepository.findByNumero(numero);

        return contaEncontrada.isPresent();
    }

    public List<Conta> findByCorrentistaId(Long id){
        return contaRepository.findByCorrentistaId(id);
    }

    public void deleteById(Long id){
        contaRepository.deleteById(id);
    }

    public Conta salvarFromDTO(ContaForm form, Correntista correntista){
        Conta conta = contaMapper.toEntity(form, correntista);
        return contaRepository.save(conta);
    }

}

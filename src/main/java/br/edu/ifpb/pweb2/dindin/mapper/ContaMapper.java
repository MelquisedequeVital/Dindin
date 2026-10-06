package br.edu.ifpb.pweb2.dindin.mapper;

import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.dindin.model.CartaoDeCredito;
import br.edu.ifpb.pweb2.dindin.model.Conta;
import br.edu.ifpb.pweb2.dindin.model.Correntista;
import br.edu.ifpb.pweb2.dindin.model.dtos.ContaForm;
import br.edu.ifpb.pweb2.dindin.model.enums.TipoConta;


@Component
public class ContaMapper {

    public Conta toEntity(ContaForm form, Correntista correntista) {
        if (TipoConta.CREDITO.equals(form.getTipoConta())) {
            CartaoDeCredito cartao = new CartaoDeCredito();
            cartao.setNumero(form.getNumero());
            cartao.setDescricao(form.getDescricao());
            cartao.setCorrentista(correntista);
            cartao.setLimiteCredito(form.getLimiteCredito());
            cartao.setDiaFechamento(form.getDiaFechamento());
            return cartao;
        }

        Conta conta = new Conta();
        conta.setNumero(form.getNumero());
        conta.setDescricao(form.getDescricao());
        conta.setCorrentista(correntista);
        return conta;
    }
}
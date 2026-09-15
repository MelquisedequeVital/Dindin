package br.edu.ifpb.pweb2.dindin.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity 
@Data 
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String descricao;

    private Usuario correntista;
    private List<Transacao> transacoes;

    public Conta() {
    }

    public Conta(String numero, String descricao, Usuario correntista) {
        this.numero = numero;
        this.descricao = descricao;
        this.correntista = correntista;
    }
}

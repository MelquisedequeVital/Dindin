package br.edu.ifpb.pweb2.dindin.model;

import br.edu.ifpb.pweb2.dindin.enums.TipoTransacao;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity 
public class Transacao {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private BigDecimal valor;
    
    private TipoTransacao movimento;
    private Conta conta;
    private Categoria categoria;
    private Comentario comentario;

    public Transacao() {
    }

    public Transacao(LocalDate data, BigDecimal valor, TipoTransacao movimento, Conta conta, Categoria categoria, Comentario comentario) {
        this.data = data;
        this.valor = valor;
        this.movimento = movimento;
        this.conta = conta;
        this.categoria = categoria;
        this.comentario = comentario;
    }
}

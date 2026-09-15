package br.edu.ifpb.pweb2.dindin.model;

import br.edu.ifpb.pweb2.dindin.model.enums.TipoTransacao;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity 
@Data 
public class Transacao {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private BigDecimal valor;
    
    @Enumerated(EnumType.STRING)
    private TipoTransacao movimento;

    @ManyToOne 
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "comentario_id", nullable = true)
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

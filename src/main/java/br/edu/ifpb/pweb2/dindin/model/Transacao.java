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
}

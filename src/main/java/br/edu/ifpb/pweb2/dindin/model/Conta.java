package br.edu.ifpb.pweb2.dindin.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity 
@Data 
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false) 
    private String numero;
    private String descricao;

    @ManyToOne 
    @JoinColumn(name = "correntista_id") 
    private Usuario correntista;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL) 
    private List<Transacao> transacoes;

    public Conta() {
    }

    public Conta(String numero, String descricao, Usuario correntista) {
        this.numero = numero;
        this.descricao = descricao;
        this.correntista = correntista;
    }

    public double calcularValorTotalGasto(){
        BigDecimal sum = BigDecimal.ZERO;
        for(Transacao trans : transacoes){
            sum = sum.add(trans.getValor());
        }

        return sum.doubleValue();
    }
}

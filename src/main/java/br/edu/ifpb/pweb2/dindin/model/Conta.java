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
    private Correntista correntista;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public Conta() {
    }

    public Conta(String numero, String descricao, Correntista correntista) {
        this.numero = numero;
        this.descricao = descricao;
        this.correntista = correntista;
    }

    public BigDecimal calcularSaldo() {
        BigDecimal valorTotal = BigDecimal.ZERO;

        if (this.transacoes == null || this.transacoes.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for (Transacao trans : this.transacoes) {
            valorTotal = valorTotal.add(trans.getImpactoFinanceiro());
        }

        return valorTotal;
    }

    public BigDecimal calcularTotalInvestido() {
        BigDecimal valorTotalInvestido = BigDecimal.ZERO;

        if (this.transacoes == null || this.transacoes.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for (Transacao trans : this.transacoes) {
            valorTotalInvestido = valorTotalInvestido.add(trans.getValorInvestido());
        }

        return valorTotalInvestido;
    }

}

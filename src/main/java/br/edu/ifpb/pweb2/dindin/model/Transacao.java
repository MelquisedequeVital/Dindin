package br.edu.ifpb.pweb2.dindin.model;

import br.edu.ifpb.pweb2.dindin.model.enums.Movimento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;
    private String descricao;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "transacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    public Transacao() {
    }

    public Transacao(LocalDate data, String descricao, BigDecimal valor, Conta conta,
            Categoria categoria) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;

        this.conta = conta;
        this.categoria = categoria;
    }

    public void addComentario(Comentario comentario){
        this.comentarios.add(comentario);
        comentario.setTransacao(this);
    }

    public BigDecimal getImpactoFinanceiro() {
        if (this.categoria == null || this.valor == null) {
            return BigDecimal.ZERO;
        }

        return this.categoria.aplicarImpactoFinanceiro(valor);
    }

    public BigDecimal getValorInvestido(){
        if(this.categoria == null) return BigDecimal.ZERO;
        return this.categoria.calcularValorInvestido(valor);
    }

    public Movimento getMovimento(){
        return this.categoria.getMovimento();
    }

}

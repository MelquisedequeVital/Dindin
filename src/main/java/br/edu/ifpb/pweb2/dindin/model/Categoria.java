package br.edu.ifpb.pweb2.dindin.model;

import java.math.BigDecimal;

import br.edu.ifpb.pweb2.dindin.model.natureza.Natureza;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "natureza_id")
    private Natureza natureza;
    // Pq as categorias podem ser ativadas e desativadas, de acordo com o documento
    // do professor:
    // Um conjunto de categorias mínimas a serem indicadas devem ser
    // obrigatoriamente já predefinidas pelo sistema e o
    // usuário administrador pode cadastrar novas ou mesmo desativar uma das
    // predefinidas (apenas o
    // administrador!).
    private Boolean ativo = true;
    private Integer ordem;

    public Categoria() {
    }

    public Categoria(String nome, Natureza natureza) {
        this.nome = nome;
        this.natureza = natureza;
        this.ativo = true;
    }

    public BigDecimal aplicarImpactoFinanceiro(BigDecimal valor) {
        return this.natureza.aplicarImpactoFinanceiro(valor);
    }
}

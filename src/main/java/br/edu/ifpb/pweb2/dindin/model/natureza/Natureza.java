package br.edu.ifpb.pweb2.dindin.model.natureza;

import java.math.BigDecimal;

import br.edu.ifpb.pweb2.dindin.model.enums.Movimento;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.NoArgsConstructor;

@Entity 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "tipo_natureza")
@NoArgsConstructor 
public abstract class Natureza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Enumerated(EnumType.STRING) 
    private Movimento movimento;

    public Natureza(Movimento movimento){
        this.movimento = movimento;
    }

    public abstract BigDecimal aplicarImpactoFinanceiro(BigDecimal valor);

    public Movimento getMovimento(){
        return this.movimento;
    }

    public BigDecimal calcularValorInvestido(BigDecimal valor){
        return BigDecimal.ZERO;
    }

}

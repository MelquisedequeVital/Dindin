package br.edu.ifpb.pweb2.dindin.model.natureza;

import java.math.BigDecimal;

import br.edu.ifpb.pweb2.dindin.model.enums.Movimento;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor 
public class Investimento extends Natureza{


    @Override
    public BigDecimal aplicarImpactoFinanceiro(BigDecimal valor) {
        return valor.negate();
    }

    @Override 
    public BigDecimal calcularValorInvestido(BigDecimal valor){
        return valor != null ? valor.abs() : BigDecimal.ZERO;
    }

    @Override 
    public Movimento getMovimento(){
        return Movimento.DEBITO;
    }

}

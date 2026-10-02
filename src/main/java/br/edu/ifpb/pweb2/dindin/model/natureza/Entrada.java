package br.edu.ifpb.pweb2.dindin.model.natureza;

import java.math.BigDecimal;

import br.edu.ifpb.pweb2.dindin.model.enums.Movimento;
import jakarta.persistence.Entity;

@Entity 
public class Entrada  extends Natureza{

    public Entrada(){
        super(Movimento.DEBITO);
    }

    @Override
    public BigDecimal aplicarImpactoFinanceiro(BigDecimal valor) {
        return valor.abs(); 
    }

}

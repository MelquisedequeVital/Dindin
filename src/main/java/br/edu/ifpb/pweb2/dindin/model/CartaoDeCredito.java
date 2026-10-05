package br.edu.ifpb.pweb2.dindin.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartaoDeCredito extends Conta {
    private Integer diaFechamento;
    private BigDecimal limiteCredito;

    public CartaoDeCredito() {
    }

    public CartaoDeCredito(Integer diaFechamento, BigDecimal limiteCredito) {
        this.diaFechamento = diaFechamento;
        this.limiteCredito = limiteCredito;
    }

    @Override
    public boolean isCartaoCredito() {
        return true;
    }

    public BigDecimal calcularLimiteDisponivel() {
        if (this.limiteCredito == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal saldoGasto = super.calcularSaldo();

        if(saldoGasto.abs().compareTo(this.limiteCredito) > 0 ){
            return BigDecimal.ZERO;
        }

        return this.limiteCredito.add(saldoGasto); 
    }

}

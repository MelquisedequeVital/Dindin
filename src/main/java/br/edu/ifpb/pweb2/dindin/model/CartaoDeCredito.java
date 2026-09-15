package br.edu.ifpb.pweb2.dindin.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity 
public class CartaoDeCredito extends Conta {
    private Integer diaFechamento;
    private BigDecimal limiteCredito;

    public CartaoDeCredito() {
    }

    public CartaoDeCredito(Integer diaFechamento, BigDecimal limiteCredito) {
        this.diaFechamento = diaFechamento;
        this.limiteCredito = limiteCredito;
    }
}

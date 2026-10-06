package br.edu.ifpb.pweb2.dindin.model.dtos;

import java.math.BigDecimal;

import br.edu.ifpb.pweb2.dindin.model.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class ContaForm {
    private String numero;
    private String descricao;
    private TipoConta tipoConta;
    private BigDecimal limiteCredito;
    private Integer diaFechamento;
}
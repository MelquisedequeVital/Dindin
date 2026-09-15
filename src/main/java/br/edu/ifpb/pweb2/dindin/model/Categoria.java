package br.edu.ifpb.pweb2.dindin.model;


import br.edu.ifpb.pweb2.dindin.model.enums.Natureza;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity 
@Data 
public class Categoria {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Natureza natureza;

    public Categoria() {
    }

    public Categoria(String nome, Natureza natureza) {
        this.nome = nome;
        this.natureza = natureza;
    }
}

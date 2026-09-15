package br.edu.ifpb.pweb2.dindin.model;

import br.edu.ifpb.pweb2.dindin.enums.Natureza;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity 
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

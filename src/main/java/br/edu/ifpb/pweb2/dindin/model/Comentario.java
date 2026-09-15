package br.edu.ifpb.pweb2.dindin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity 
@Data 
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private Transacao transacao;

    public Comentario() {
    }

    public Comentario(String texto, Transacao transacao) {
        this.texto = texto;
        this.transacao = transacao;
    }
}

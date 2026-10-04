package br.edu.ifpb.pweb2.dindin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity 
@Data 
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    @ManyToOne
    @JoinColumn(name = "transacao_id")
    private Transacao transacao;

    public Comentario() {
    }

    public Comentario(String texto, Transacao transacao) {
        this.texto = texto;
        this.transacao = transacao;
    }
}

package br.edu.ifpb.pweb2.dindin.model;

import java.util.List;

import br.edu.ifpb.pweb2.dindin.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity 
@Data 
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Role role;
    private boolean bloqueado;
    private List<Transacao> transacoes;

    public Usuario() {
    }

    public Usuario(String nome, String login, String senha, Role role, boolean bloqueado) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.role = role;
        this.bloqueado = bloqueado;
    }
}

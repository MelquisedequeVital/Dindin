package br.edu.ifpb.pweb2.dindin.model;

import java.util.List;

import br.edu.ifpb.pweb2.dindin.model.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity 
@Data 
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false) 
    private String username;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean bloqueado;
    @OneToMany(mappedBy = "correntista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
    private List<Conta> contas;

    public Usuario() {
    }

    public Usuario(String nome, String username, String senha, Role role, boolean bloqueado) {
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.role = role;
        this.bloqueado = bloqueado;
    }

    public void addConta(Conta conta){
        this.contas.add(conta);
    }
}

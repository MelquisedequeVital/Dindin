package br.edu.ifpb.pweb2.dindin.model;


import br.edu.ifpb.pweb2.dindin.model.enums.Natureza;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private Natureza natureza;
    //Pq as categorias podem ser ativadas e desativadas, de acordo com o documento do professor:
    //Um conjunto de categorias mínimas a serem indicadas devem ser obrigatoriamente já predefinidas pelo sistema e o
    //usuário administrador pode cadastrar novas ou mesmo desativar uma das predefinidas (apenas o
    //administrador!).
    private Boolean ativo = true;

    public Categoria() {
    }

    public Categoria(String nome, Natureza natureza) {
        this.nome = nome;
        this.natureza = natureza;
        this.ativo = true;
    }
}

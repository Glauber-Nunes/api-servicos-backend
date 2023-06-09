package sistemagn.servicos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @CPF(message = "CPF '${validatedValue}' é invalido!")
    private String cpf;
    @Email
    private String email;
    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Servico> servicoList = new ArrayList<>();
    public Cliente(Long id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
}

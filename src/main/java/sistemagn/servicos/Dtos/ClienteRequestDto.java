package sistemagn.servicos.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Servico;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDto {
    private Long id;
    @NotNull(message = "Campo Nome Requerido")
    @NotEmpty(message = "Informe O Nome Do Cliente")
    private String nome;
    @CPF(message = "CPF informado inválido")
    @NotNull(message = "Campo CPF Requerido")
    @NotEmpty(message = "Informe O CPF Do Cliente")
    private String cpf;
    @Email(message = "Email Invalido")
    private String email;
    private List<Servico> servicoList = new ArrayList<>();
}

package sistemagn.servicos.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sistemagn.servicos.Enums.Status;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Funcionario;
import sistemagn.servicos.entities.Servico;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicoForm {

    private Long id;
    @NotNull(message = "Informe A Descrição Do Serviço")
    private String descricaoServico;
    @NotNull(message = "Informe O Valor Do Serviço")
    private Double valorServico;
    private String protocolo;
    private Status status;
    private Cliente cliente;
    private Funcionario funcionario;


}

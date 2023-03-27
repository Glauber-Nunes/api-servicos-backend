package sistemagn.servicos.Dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sistemagn.servicos.Enums.Status;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Servico;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicoRequestDto {

    private Long id;
    private Date dataInicio;
    @NotNull(message = "Informe A Descrição Do Serviço")
    private String descricaoServico;
    @NotNull(message = "Informe O Valor Do Serviço")
    private Double valorServico;
    private String protocolo;
    private Status status;
    private Date dataFechamento;
    private Cliente cliente;

    public ServicoRequestDto(Servico servico) {
        this.id = servico.getId();
        this.dataInicio = servico.getDataInicio();
        this.valorServico = servico.getValorServico();
        this.protocolo = servico.getProtocolo();
        this.status = servico.getStatus();
        this.cliente = servico.getCliente();
    }
}

package sistemagn.servicos.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import sistemagn.servicos.Enums.Status;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Servico;

import java.util.Date;

@Data
@NoArgsConstructor
public class ServicoDtoget {

    private Date dataInicio;
    private String descricaoServico;
    private Double valorServico;
    private String protocolo;
    private Status status;
    private Cliente cliente;

    public ServicoDtoget(Servico servico) {
        this.dataInicio = servico.getDataInicio();
        this.descricaoServico = servico.getDescricaoServico();
        this.valorServico = servico.getValorServico();
        this.protocolo = servico.getProtocolo();
        this.status = servico.getStatus();
        this.cliente = servico.getCliente();
    }
}

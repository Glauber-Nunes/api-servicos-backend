package sistemagn.servicos.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import sistemagn.servicos.Enums.Status;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Funcionario;
import sistemagn.servicos.entities.Servico;

import java.util.Date;

@Data
@NoArgsConstructor
public class ServicoView {

    private Date dataInicio;
    private String descricaoServico;
    private Double valorServico;
    private String protocolo;
    private Status status;
    private Cliente cliente;
    private Funcionario funcionario;

    public ServicoView(Servico servico) {
        this.dataInicio = servico.getDataInicio();
        this.descricaoServico = servico.getDescricaoServico();
        this.valorServico = servico.getValorServico();
        this.protocolo = servico.getProtocolo();
        this.status = servico.getStatus();
        this.cliente = servico.getCliente();
        this.funcionario = servico.getFuncionario();
    }
}

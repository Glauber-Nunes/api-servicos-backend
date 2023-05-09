package sistemagn.servicos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sistemagn.servicos.Enums.Status;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    private Date dataAtualizacao;
    private String descricaoServico;
    private Double valorServico;
    private String protocolo;
    private Status status;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataFechamento;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcinario_id")
    private Funcionario funcionario;

    public Servico(Long id, Date dataInicio, Date dataAtualizacao, String descricaoServico, Double valorServico, String protocolo, Cliente cliente, Funcionario funcionario) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataAtualizacao = dataAtualizacao;
        this.descricaoServico = descricaoServico;
        this.valorServico = valorServico;
        this.protocolo = protocolo;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }
}

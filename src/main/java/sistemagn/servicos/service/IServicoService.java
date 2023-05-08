package sistemagn.servicos.service;

import sistemagn.servicos.Dtos.ServicoForm;
import sistemagn.servicos.Dtos.ServicoView;
import sistemagn.servicos.entities.Servico;

import java.util.List;

public interface IServicoService {
    Servico save(Long id_cliente, Long id_funcionario, ServicoForm newObj);

    List<ServicoView> findAll();

    ServicoView findByProtocolo(String protocolo);

    Servico findById(Long id);

    void finalizarServico(Servico servico);

    Servico update(Long id_servico, Long id_cliente, ServicoForm newObj);

    void delete(Long id);
}

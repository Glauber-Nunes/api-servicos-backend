package sistemagn.servicos.service;

import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Servico;

public interface IEmailService {
    void enviarEmailServicoAberto(Cliente cliente, Servico servico);
    void enviarEmailServicoFinalizado(Servico servico);
}

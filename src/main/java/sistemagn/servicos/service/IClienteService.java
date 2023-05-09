package sistemagn.servicos.service;

import sistemagn.servicos.Dtos.ClienteForm;
import sistemagn.servicos.Dtos.ClienteView;
import sistemagn.servicos.entities.Cliente;

import java.util.List;

public interface IClienteService {


    Cliente save(ClienteForm clienteForm);

    List<ClienteView> findAll();

    Cliente findById(Long id);

    Cliente update(Long id_cliente, ClienteForm newObj);

    void delete(Long id);

    List<ClienteView> findByNome(String nome);

    ClienteView findByCpf(String cpf);
}

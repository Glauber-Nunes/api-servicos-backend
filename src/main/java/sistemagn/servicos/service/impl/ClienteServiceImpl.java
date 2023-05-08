package sistemagn.servicos.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemagn.servicos.Dtos.ClienteView;
import sistemagn.servicos.Dtos.ClienteForm;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.repository.ClienteRepository;
import sistemagn.servicos.service.IClienteService;
import sistemagn.servicos.service.exceptions.NotFoundException;
import sistemagn.servicos.service.exceptions.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente save(ClienteForm newObj) {

        this.findByExistsCPF(newObj); // verifica se o cpf existe no banco de dados

        return clienteRepository.save(Cliente.builder()
                .id(null)
                .nome(newObj.getNome())
                .cpf(newObj.getCpf())
                .email(newObj.getEmail())
                .build());
    }

    @Override
    public List<ClienteView> findAll() {

        List<Cliente> clienteList = clienteRepository.findAll();

        List<ClienteView> clienteViewList = clienteList.stream().map(cliente -> new ClienteView(cliente)).collect(Collectors.toList());

        return clienteViewList;
    }

    @Override
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new NotFoundException("Cliente Não Encontrado"));
    }

    @Override
    @Transactional
    public Cliente update(Long id_cliente, ClienteForm newObj) {

        Cliente clienteBanco = this.findById(id_cliente);


        Cliente cliente = Cliente.builder()
                .id(clienteBanco.getId())
                .nome(newObj.getNome() != null ? newObj.getNome() : clienteBanco.getNome())
                .cpf(newObj.getCpf() != null ? newObj.getCpf() : clienteBanco.getCpf())
                .email(newObj.getEmail() != null ? newObj.getEmail() : clienteBanco.getEmail())
                .servicoList(clienteBanco.getServicoList())
                .build();

        this.findByExistsCPF(newObj); // verifica se o cpj ja existe

        return cliente;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Cliente cliente = this.findById(id);

        clienteRepository.delete(cliente);
    }

    @Override
    public List<ClienteView> findByNome(String nome) {

        List<Cliente> clientesList = clienteRepository.findByNome(nome);

        List<ClienteView> ClientedtoList = clientesList.stream().map(cliente -> new ClienteView(cliente)).collect(Collectors.toList());

        if (ClientedtoList.size() == 0) throw new NotFoundException("Nenhum Resultado Para: " + nome);

        return ClientedtoList;
    }

    @Override
    public ClienteView findByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);

        if (cliente == null) {
            throw new NotFoundException("Cliente Não Encontrado");
        }

        return new ClienteView(cliente);
    }

    private void findByExistsCPF(ClienteForm clienteForm) {

        if (clienteRepository.existsByCpf(clienteForm.getCpf())) {
            throw new DataIntegrityViolationException("CPF JA CADASTRADO NA BASE DE DADOS");
        }

    }

}

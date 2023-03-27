package sistemagn.servicos.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemagn.servicos.Dtos.ClienteGetDto;
import sistemagn.servicos.Dtos.ClienteRequestDto;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.repository.ClienteRepository;
import sistemagn.servicos.service.exceptions.NotFoundException;
import sistemagn.servicos.service.exceptions.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(ClienteRequestDto newObj) {

        this.findByExistsCPF(newObj);

        return clienteRepository.save(Cliente.builder()
                .id(null)
                .nome(newObj.getNome())
                .cpf(newObj.getCpf())
                .email(newObj.getEmail())
                .build());
    }

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new NotFoundException("Cliente Não Encontrado"));
    }

    @Transactional
    public Cliente update(Long id_cliente, ClienteRequestDto newObj) {

        Cliente clienteBanco = this.findById(id_cliente);

        this.findByExistsCPF(newObj); // verifica se o cpj ja existe

        return clienteRepository.save(Cliente.builder()
                .id(clienteBanco.getId())
                .nome(newObj.getNome() != null ? newObj.getNome() : clienteBanco.getNome())
                .cpf(newObj.getCpf() != null ? newObj.getCpf() : clienteBanco.getCpf())
                .email(newObj.getEmail() != null ? newObj.getEmail() : clienteBanco.getEmail())
                .servicoList(clienteBanco.getServicoList())
                .build());
    }

    @Transactional
    public void delete(Long id) {
        Cliente cliente = this.findById(id);

        clienteRepository.delete(cliente);
    }

    public List<ClienteGetDto> findByNome(String nome) {
        List<Cliente> clientesList = clienteRepository.findByNome(nome);

        List<ClienteGetDto> ClientedtoList = clientesList.stream().map(cliente -> new ClienteGetDto(cliente)).collect(Collectors.toList());

        if (ClientedtoList.size() == 0) throw new NotFoundException("Nenhum Resultado Para: " + nome);

        return ClientedtoList;
    }

    private void findByExistsCPF(ClienteRequestDto clienteRequestDto) {

        if (clienteRepository.existsByCpf(clienteRequestDto.getCpf())) {
            throw new DataIntegrityViolationException("CPF JA CADASTRADO NA BASE DE DADOS");
        }

    }

}

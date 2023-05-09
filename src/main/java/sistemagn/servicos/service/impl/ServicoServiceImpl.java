package sistemagn.servicos.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemagn.servicos.Dtos.ServicoView;
import sistemagn.servicos.Dtos.ServicoForm;
import sistemagn.servicos.Enums.Status;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Funcionario;
import sistemagn.servicos.entities.Servico;
import sistemagn.servicos.repository.ClienteRepository;
import sistemagn.servicos.repository.ServicoRepository;
import sistemagn.servicos.service.IClienteService;
import sistemagn.servicos.service.IEmailService;
import sistemagn.servicos.service.IFuncionarioService;
import sistemagn.servicos.service.IServicoService;
import sistemagn.servicos.service.exceptions.NotFoundException;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoServiceImpl implements IServicoService {
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IFuncionarioService  iFuncionarioService;
    @Autowired
    private IEmailService iEmailService;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    @Override
    public Servico save(Long id_cliente, Long id_funcionario, ServicoForm servicoForm) {

        Cliente cliente = iClienteService.findById(id_cliente);
        Funcionario funcionario = iFuncionarioService.findById(id_funcionario);

        Servico servico = Servico.builder()
                .id(null)
                .dataInicio(new Date())
                .descricaoServico(servicoForm.getDescricaoServico())
                .valorServico(servicoForm.getValorServico())
                .protocolo(this.gerarProtocolo())
                .status(Status.ABERTO)
                .cliente(cliente)
                .funcionario(funcionario)
                .build();

        servicoRepository.save(servico);

        iEmailService.enviarEmailServicoAberto(cliente, servico); // envia email

        return servico;
    }

    @Override
    public List<ServicoView> findAll() {

        List<Servico> servicoList = servicoRepository.findAll();

        List<ServicoView> servicoDtogetsList = servicoList.stream().map(servico -> new ServicoView(servico)).collect(Collectors.toList());

        return servicoDtogetsList;

    }

    @Override
    public ServicoView findByProtocolo(String protocolo) {

        Servico servico = servicoRepository.findByProtocolo(protocolo);

        if (servico == null) throw new NotFoundException("Serviço Não Encontrado");

        return new ServicoView(servico);

    }

    @Override
    public Servico findById(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);

        return servico.orElseThrow(() -> new NotFoundException("Serviço Não Encontrado"));
    }

    @Override
    public void finalizarServico(Servico servico) {

        Servico servicoBanco = servicoRepository.findById(servico.getId()).get();
        servicoBanco.setStatus(Status.FECHADO);
        servicoBanco.setDataFechamento(new Date());
        servicoRepository.save(servicoBanco);

        //APOS FINALIZAR O SERVIÇO ENVIA EMAIL PARA O CLIENTE AVISANDO SOBRE O ENCERRAMENTO DO SERVIÇO
        iEmailService.enviarEmailServicoFinalizado(servicoBanco);
    }

    @Override
    @Transactional
    public Servico update(Long id_servico, Long id_cliente, ServicoForm newServicoForm) {

        Servico servicoBanco = this.findById(id_servico);

        Cliente cliente = iClienteService.findById(id_cliente);

        return servicoRepository.save(Servico.builder()
                .id(servicoBanco.getId())
                .dataInicio(servicoBanco.getDataInicio())
                .descricaoServico(newServicoForm.getDescricaoServico() != null ? newServicoForm.getDescricaoServico() : servicoBanco.getDescricaoServico())
                .dataAtualizacao(new Date())
                .valorServico(newServicoForm.getValorServico() != null ? newServicoForm.getValorServico() : servicoBanco.getValorServico())
                .protocolo(servicoBanco.getProtocolo())
                .status(servicoBanco.getStatus())
                .dataFechamento(servicoBanco.getDataFechamento())
                .cliente(cliente != null ? cliente : servicoBanco.getCliente())
                .build());

    }

    @Override
    public void delete(Long id) {
        Servico servico = this.findById(id);
        servicoRepository.delete(servico);
    }

    private String gerarProtocolo() {
        String CHARACTERS = "0123456789";
        int CODE_LENGTH = 7;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }


}

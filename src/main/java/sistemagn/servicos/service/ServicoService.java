package sistemagn.servicos.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemagn.servicos.Dtos.ServicoDtoget;
import sistemagn.servicos.Dtos.ServicoRequestDto;
import sistemagn.servicos.Enums.Status;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Servico;
import sistemagn.servicos.repository.ClienteRepository;
import sistemagn.servicos.repository.ServicoRepository;
import sistemagn.servicos.service.exceptions.NotFoundException;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Servico save(Long id_cliente, ServicoRequestDto newObj) {

        Cliente cliente = clienteService.findById(id_cliente);

        Servico servico = Servico.builder()
                .id(null)
                .dataInicio(new Date())
                .dataAtualizacao(null)
                .descricaoServico(newObj.getDescricaoServico())
                .valorServico(newObj.getValorServico())
                .protocolo(newObj.getProtocolo())
                .status(Status.ABERTO)
                .cliente(cliente)
                .build();

        servicoRepository.save(servico);

        emailService.enviarEmailServicoAberto(cliente, servico); // envia email

        return servico;
    }

    public ServicoDtoget findByProtocolo(String protocolo) {

        Servico servico = servicoRepository.findByProtocolo(protocolo);

        if (servico == null) throw new NotFoundException("Serviço Não Encontrado");

        return new ServicoDtoget(servico);

    }

    public Servico findById(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);

        return servico.orElseThrow(() -> new NotFoundException("Serviço Não Encontrado"));
    }

    public void finalizarServico(Servico servico) {

        Servico servicoBanco = servicoRepository.findById(servico.getId()).get();
        servicoBanco.setStatus(Status.FECHADO);
        servicoBanco.setDataFechamento(new Date());
        servicoRepository.save(servicoBanco);

        //APOS FINALIZAR O SERVIÇO ENVIA EMAIL PARA O CLIENTE AVISANDO SOBRE O ENCERRAMENTO DO SERVIÇO
        emailService.enviarEmailServicoFinalizado(servicoBanco);
    }

    @Transactional
    public Servico update(Long id_servico, Long id_cliente, ServicoRequestDto newObj) {

        Servico servicoBanco = this.findById(id_servico);

        Cliente cliente = clienteService.findById(id_cliente);

        return servicoRepository.save(Servico.builder()
                .id(servicoBanco.getId())
                .dataInicio(servicoBanco.getDataInicio())
                .descricaoServico(newObj.getDescricaoServico() != null ? newObj.getDescricaoServico() : servicoBanco.getDescricaoServico())
                .dataAtualizacao(new Date())
                .valorServico(newObj.getValorServico() != null ? newObj.getValorServico() : servicoBanco.getValorServico())
                .protocolo(servicoBanco.getProtocolo())
                .status(servicoBanco.getStatus())
                .dataFechamento(servicoBanco.getDataFechamento())
                .cliente(cliente != null ? cliente : servicoBanco.getCliente())
                .build());

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

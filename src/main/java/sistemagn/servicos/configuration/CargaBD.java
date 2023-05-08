package sistemagn.servicos.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.entities.Funcionario;
import sistemagn.servicos.entities.Servico;
import sistemagn.servicos.repository.ClienteRepository;
import sistemagn.servicos.repository.FuncionarioRepository;
import sistemagn.servicos.repository.ServicoRepository;

import java.util.Arrays;
import java.util.Date;

@Component
public class CargaBD implements CommandLineRunner {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public void run(String... args) throws Exception {

        //CLIENTES
        Cliente cliente1 = new Cliente(null, "Carlos Augusto", "155.695.074-85", "nglauber53@gmail.com");
        Cliente cliente2 = new Cliente(null, "Renato Augusto", "678.117.390-11", "renato@gmail.com");

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));

        //FUNCIONARIOS
        Funcionario funcionario1 = new Funcionario(null, "Renato Silva", "659.309.260-69", "MECANICO");
        funcionarioRepository.save(funcionario1);

        Servico servico = new Servico(null, new Date(), null, "troca de oleo", 150D, "45454545", cliente2, funcionario1);
        servicoRepository.save(servico);

    }
}

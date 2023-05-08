package sistemagn.servicos.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemagn.servicos.Dtos.FuncionarioForm;
import sistemagn.servicos.entities.Funcionario;
import sistemagn.servicos.repository.FuncionarioRepository;
import sistemagn.servicos.service.IFuncionarioService;
import sistemagn.servicos.service.exceptions.NotFoundException;

@Service
public class FuncionarioServiceImpl implements IFuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    @Override
    public Funcionario save(FuncionarioForm funcionarioForm) {

        Funcionario funcionario = Funcionario.builder()
                .id(null)
                .nome(funcionarioForm.getNome())
                .cpf(funcionarioForm.getCpf())
                .cargo(funcionarioForm.getCargo())
                .build();

        funcionarioRepository.save(funcionario);

        return funcionario;

    }
    @Override
    @Transactional
    public Funcionario update(Long id, FuncionarioForm funcionarioForm) {

        Funcionario funcionarioBanco = this.findById(id);

        Funcionario funcionario = Funcionario.builder()
                .id(funcionarioBanco.getId())
                .nome(funcionarioForm.getNome())
                .cpf(funcionarioForm.getCpf())
                .cargo(funcionarioForm.getCargo())
                .build();

        return funcionarioRepository.saveAndFlush(funcionario);

    }
    @Override
    public Funcionario findById(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).get();

        if (funcionario == null) throw new NotFoundException("Funcionario Não Encontrado");

        return funcionario;
    }
}

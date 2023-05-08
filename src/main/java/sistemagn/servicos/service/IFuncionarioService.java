package sistemagn.servicos.service;

import sistemagn.servicos.Dtos.FuncionarioForm;
import sistemagn.servicos.entities.Funcionario;

public interface IFuncionarioService {
    Funcionario save(FuncionarioForm funcionarioForm);

    Funcionario update(Long id, FuncionarioForm funcionarioForm);

    Funcionario findById(Long id);
}

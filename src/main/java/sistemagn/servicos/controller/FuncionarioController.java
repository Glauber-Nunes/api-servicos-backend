package sistemagn.servicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemagn.servicos.Dtos.FuncionarioForm;
import sistemagn.servicos.entities.Funcionario;
import sistemagn.servicos.service.IFuncionarioService;
import sistemagn.servicos.service.impl.FuncionarioServiceImpl;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private IFuncionarioService iFuncionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody FuncionarioForm funcionarioForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iFuncionarioService.save(funcionarioForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody FuncionarioForm funcionarioForm) {
        return ResponseEntity.status(HttpStatus.OK).body(iFuncionarioService.update(id, funcionarioForm));
    }

    @GetMapping("/{id}")

    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iFuncionarioService.findById(id));
    }
}

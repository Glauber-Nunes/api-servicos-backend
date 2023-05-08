package sistemagn.servicos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemagn.servicos.Dtos.ClienteView;
import sistemagn.servicos.Dtos.ClienteForm;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.service.IClienteService;
import sistemagn.servicos.service.impl.ClienteServiceImpl;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteForm newObj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iClienteService.save(newObj));
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(iClienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iClienteService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable long id, @RequestBody ClienteForm newObj) {
        return ResponseEntity.status(HttpStatus.OK).body(iClienteService.update(id, newObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        iClienteService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Cliente Deletado Com Sucesso");
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ClienteView>> findByNome(@PathVariable String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(iClienteService.findByNome(nome));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteView> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(iClienteService.findByCpf(cpf));
    }
}

package sistemagn.servicos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemagn.servicos.Dtos.ClienteGetDto;
import sistemagn.servicos.Dtos.ClienteRequestDto;
import sistemagn.servicos.entities.Cliente;
import sistemagn.servicos.service.ClienteService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteRequestDto newObj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(newObj));
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteGetDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable long id,@RequestBody ClienteRequestDto newObj) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(id, newObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clienteService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Cliente Deletado Com Sucesso");
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ClienteGetDto>> findByNome(@PathVariable String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByNome(nome));
    }
}

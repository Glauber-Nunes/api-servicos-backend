package sistemagn.servicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemagn.servicos.Dtos.ServicoDtoget;
import sistemagn.servicos.Dtos.ServicoRequestDto;
import sistemagn.servicos.entities.Servico;
import sistemagn.servicos.service.ServicoService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Servico> save(@RequestParam(value = "cliente", defaultValue = "0") Long id
            , @RequestBody ServicoRequestDto servicoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.save(id, servicoRequestDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ServicoDtoget>> findAl() {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.findAll());
    }

    @GetMapping("/protocolo/{protocolo}")
    public ResponseEntity<ServicoDtoget> findByProtocolo(@PathVariable String protocolo) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.findByProtocolo(protocolo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.findById(id));
    }

    @PostMapping("/finalizar-servico")
    public ResponseEntity<String> finalizarServico(@RequestBody Servico servico) {
        servicoService.finalizarServico(servico);
        return ResponseEntity.status(HttpStatus.OK).body("SERVIÇO FINALIZADO COM SUCESSO");
    }

    @PutMapping
    public ResponseEntity<Servico> update(@RequestParam(value = "servico_id") Long servico_id,
                                          @RequestParam(value = "cliente_id", defaultValue = "0") Long cliente_id,
                                          @RequestBody ServicoRequestDto servicoRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.update(servico_id, cliente_id, servicoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Serviço Deletado Com Sucesso!");
    }
}

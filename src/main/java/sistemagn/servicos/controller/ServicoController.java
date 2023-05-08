package sistemagn.servicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemagn.servicos.Dtos.ServicoView;
import sistemagn.servicos.Dtos.ServicoForm;
import sistemagn.servicos.entities.Servico;
import sistemagn.servicos.service.IClienteService;
import sistemagn.servicos.service.IServicoService;
import sistemagn.servicos.service.impl.ServicoServiceImpl;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private IServicoService iServicoService;

    @PostMapping
    public ResponseEntity<Servico> save(@RequestParam(value = "cliente", defaultValue = "0") Long id, @RequestParam(value = "funcionario", defaultValue = "0")
    Long id_funcionario, @RequestBody ServicoForm servicoForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iServicoService.save(id, id_funcionario, servicoForm));
    }

    @GetMapping("/")
    public ResponseEntity<List<ServicoView>> findAl() {
        return ResponseEntity.status(HttpStatus.OK).body(iServicoService.findAll());
    }

    @GetMapping("/protocolo/{protocolo}")
    public ResponseEntity<ServicoView> findByProtocolo(@PathVariable String protocolo) {
        return ResponseEntity.status(HttpStatus.OK).body(iServicoService.findByProtocolo(protocolo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iServicoService.findById(id));
    }

    @PostMapping("/finalizar-servico")
    public ResponseEntity<String> finalizarServico(@RequestBody Servico servico) {
        iServicoService.finalizarServico(servico);
        return ResponseEntity.status(HttpStatus.OK).body("SERVIÇO FINALIZADO COM SUCESSO");
    }

    @PutMapping
    public ResponseEntity<Servico> update(@RequestParam(value = "servico_id") Long servico_id,
                                          @RequestParam(value = "cliente_id", defaultValue = "0") Long cliente_id,
                                          @RequestBody ServicoForm servicoForm) {
        return ResponseEntity.status(HttpStatus.OK).body(iServicoService.update(servico_id, cliente_id, servicoForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        iServicoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Serviço Deletado Com Sucesso!");
    }
}

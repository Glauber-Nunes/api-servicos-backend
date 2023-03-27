package sistemagn.servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemagn.servicos.entities.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    Servico findByProtocolo(String protocolo);
}
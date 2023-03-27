package sistemagn.servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sistemagn.servicos.entities.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("select c from Cliente c where lower(c.nome) like lower(concat('%', ?1,'%'))")
    List<Cliente> findByNome(String nome);

    boolean existsByCpf(String cpf); // verifica se o cpf ja esta cadastardo no banco de dados
    boolean existsByEmail(String email);
}
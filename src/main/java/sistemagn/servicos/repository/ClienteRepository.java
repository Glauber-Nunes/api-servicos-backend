package sistemagn.servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sistemagn.servicos.entities.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(concat('%', ?1,'%'))")
    List<Cliente> findByNome(String nome);
    boolean existsByCpf(String cpf); // verifica se o cpf ja esta cadastardo no banco de dados
    boolean existsByEmail(String email);
    Cliente findByCpf(String cpf);
}
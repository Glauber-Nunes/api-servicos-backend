package sistemagn.servicos.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sistemagn.servicos.entities.Cliente;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteGetDto {

    private String nome;
    private String email;
    public ClienteGetDto(Cliente cliente){
        this.nome= cliente.getNome();
        this.email= cliente.getEmail();
    }

}

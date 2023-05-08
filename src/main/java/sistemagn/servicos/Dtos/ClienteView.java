package sistemagn.servicos.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sistemagn.servicos.entities.Cliente;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteView {

    private String nome;
    private String email;
    public ClienteView(Cliente cliente){
        this.nome= cliente.getNome();
        this.email= cliente.getEmail();
    }

}

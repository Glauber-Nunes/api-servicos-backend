package sistemagn.servicos.controller.exceptions;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FieldMessage implements Serializable {
    private String fieldName;
    private String message;

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}

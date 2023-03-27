package sistemagn.servicos.controller.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError extends StandardError implements Serializable {

    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError(Instant timestamp, int status, String Error, String messenger, String path) {
        super(timestamp, status, Error, messenger, path);
    }

    public ValidationError() {
        super();
    }

    public void addErro(String fieldName, String message) {
        this.fieldMessages.add(new FieldMessage(fieldName, message));
    }
}

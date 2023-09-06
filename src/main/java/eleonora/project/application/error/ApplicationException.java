package eleonora.project.application.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationException extends RuntimeException {

    private ErrorResponse error;

    public ApplicationException(String message) {
        this.error = new ErrorResponse();
        this.error.setStatusCode(Integer.getInteger("500"));
        this.error.setDescription(message);
    }

    public ApplicationException(HttpStatus status, String message) {
        this.error = new ErrorResponse();
        this.error.setStatusCode(status.value());
        this.error.setDescription(message);
    }


}

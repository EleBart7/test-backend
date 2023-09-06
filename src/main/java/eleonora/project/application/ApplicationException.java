package eleonora.project.application;

import eleonora.project.application.error.ErrorResponse;

public class ApplicationException extends RuntimeException {

    private ErrorResponse error;

    public ApplicationException(String message) {
        this.error = new ErrorResponse();
        this.error.setCode("500");
        this.error.setDescription(message);
    }

}

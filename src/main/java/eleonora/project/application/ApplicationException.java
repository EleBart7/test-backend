package eleonora.project.application;

public class ApplicationException extends RuntimeException {

    private String message;

    public ApplicationException(String message) {
        this.message = message;
    }

}

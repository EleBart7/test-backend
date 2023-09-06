package eleonora.project.application.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@RequiredArgsConstructor
public class ApplicationErrorHandler {


	@ExceptionHandler(value = { ApplicationException.class })
	protected ResponseEntity<Object> handleContiException(ApplicationException ex) {

		ApplicationException exApp = (ApplicationException) ex;
		ErrorResponse error = exApp.getError();
		StringWriter sw = new StringWriter();
		exApp.printStackTrace(new PrintWriter(sw));

		return new ResponseEntity<Object>(error, HttpStatus.valueOf(error.getStatusCode()));
	}

}

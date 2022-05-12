package in.gf2.seniorplaza.domain.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.gf2.seniorplaza.domain.exception.NegocioException;

@ControllerAdvice
public class ExceptionHandlerUnit extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException exception, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var headers = new HttpHeaders();
		return super.handleExceptionInternal(exception, exception.getMessage(), headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.set("Content-Type", "application/json;charset=utf-8");
		var problem = new Problem();
		problem.setMessage("Um ou mais campos inválidos.");
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			problem.addCampo(((FieldError) error).getField(), error.getDefaultMessage());
		}
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}
}

package med.voll.api.infra.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErrores {

//	cuando se introduce una url que no existe
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarError404() {
		return ResponseEntity.notFound().build();
	}
	
//	cuando se agrega un registro con datos principales faltantes (nombre, email, documento)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
		var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
		return ResponseEntity.badRequest().body(errores);
	}
	
	private record DatosErrorValidacion(String campo, String error) {
		public DatosErrorValidacion(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}

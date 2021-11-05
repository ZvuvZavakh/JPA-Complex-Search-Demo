package zvuv.zavakh.jpacomplexsearchdemo.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        HashMap<String, String> errors = new HashMap<>();
        BindingResult bindingResult = exception.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult
                    .getAllErrors()
                    .forEach(objectError -> {
                        FieldError fieldError = (FieldError) objectError;
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                    });
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEmptyRequestException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>("Empty request", HttpStatus.BAD_REQUEST);
    }
}

package ru.manasyan.advertising;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.manasyan.advertising.data.dto.ErrorDto;
import ru.manasyan.advertising.exceptions.AlreadyExistsException;
import ru.manasyan.advertising.exceptions.CategoryDeleteException;
import ru.manasyan.advertising.exceptions.NotFoundException;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFound(NotFoundException exc) {
        return error(
                ErrorDto.ErrorType.NOT_FOUND,
                exc.getMessage()
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleAlreadyExists(AlreadyExistsException exc) {
        return error(
                ErrorDto.ErrorType.ALREADY_EXISTS,
                exc.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationError(MethodArgumentNotValidException exc) {
        FieldError fieldError = exc.getFieldError();
        return error(
                ErrorDto.ErrorType.VALIDATION_FAIL,
                fieldError == null ? "" : "Validation failed in field: " + fieldError.getField()
        );
    }

    @ExceptionHandler(CategoryDeleteException.class)
    public ResponseEntity<ErrorDto> handleCategoryDelete(CategoryDeleteException exc) {
        return error(
                ErrorDto.ErrorType.CATEGORY_DELETE,
                exc.getMessage()
        );
    }

    private ResponseEntity<ErrorDto> error(ErrorDto.ErrorType type, String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(
                        type,
                        message
                ));
    }
}

package ru.manasyan.advertising;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.manasyan.advertising.data.dto.ContentDto;
import ru.manasyan.advertising.data.dto.ErrorDto;
import ru.manasyan.advertising.exceptions.AlreadyExistsException;
import ru.manasyan.advertising.exceptions.CategoryRemovalException;
import ru.manasyan.advertising.exceptions.NoMoreBannersException;
import ru.manasyan.advertising.exceptions.NotFoundException;

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(NoMoreBannersException.class)
    public ResponseEntity<ContentDto> handleNoMoreBanners() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFound(NotFoundException exc) {
        return error(
                ErrorDto.ErrorType.NOT_FOUND,
                exc.getLocalizedMessage()
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleAlreadyExists(AlreadyExistsException exc) {
        return error(
                ErrorDto.ErrorType.ALREADY_EXISTS,
                exc.getLocalizedMessage()
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

    @ExceptionHandler(CategoryRemovalException.class)
    public ResponseEntity<ErrorDto> handleCategoryDelete(CategoryRemovalException exc) {
        return error(
                ErrorDto.ErrorType.CATEGORY_DELETE,
                exc.getLocalizedMessage()
        );
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<ErrorDto> handleBindingError(ServletRequestBindingException exc) {
        return error(
                ErrorDto.ErrorType.VALIDATION_FAIL,
                exc.getLocalizedMessage()
        );
    }

    private ResponseEntity<ErrorDto> error(ErrorDto.ErrorType type, String message) {
        log.error("Error: [{}] : {}", type, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(
                        type,
                        message
                ));
    }
}

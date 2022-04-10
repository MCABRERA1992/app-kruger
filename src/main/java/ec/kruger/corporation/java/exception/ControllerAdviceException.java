package ec.kruger.corporation.java.exception;

import ec.kruger.corporation.java.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:47
 * @project app-vacunacion
 * @Version 1.0
 **/
@RestControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler(KrugerException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto resourceNotFoundException(KrugerException asosercadeposException, WebRequest webRequest) {
        ErrorDto errorDto = ErrorDto.builder()
                .codeError(HttpStatus.NOT_FOUND.value())
                .messageError(asosercadeposException.getMessage())
                .uriError(webRequest.getDescription(false))
                .timeStampError(new SimpleDateFormat("dd-MM-yyyy - hh:mm:ss").format(new Date()))
                .build();
        return errorDto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto globalExceptionHandler(Exception exception, WebRequest webRequest) {
        ErrorDto errorDto = ErrorDto.builder()
                .codeError(HttpStatus.NOT_FOUND.value())
                .messageError(exception.getMessage())
                .uriError(webRequest.getDescription(false))
                .timeStampError(new SimpleDateFormat("dd-MM-yyyy - hh:mm:ss").format(new Date()))
                .build();
        return errorDto;
    }
}

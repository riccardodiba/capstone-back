package riccardodiba.capstoneBack.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import riccardodiba.capstoneBack.payloads.exception.ErrorPayload;
import riccardodiba.capstoneBack.payloads.exception.ErrorPayloadWithListDTO;


import java.util.Date;
import java.util.List;



@RestControllerAdvice
public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handleNotFound(NotFoundException e) {
        return new ErrorPayload(e.getMessage(), new Date());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleBadRequest(BadRequestException e) {

        if (e.getErrorList() != null) {
            List<String> errorsList = e.getErrorList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorPayloadWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorPayload(e.getMessage(), new Date());
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleIllegalArgument(IllegalArgumentException e) {
        return new ErrorPayload("Argument not Valid!", new Date());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorPayload handleUnauthorized(UnauthorizedException e) {
        return new ErrorPayload(e.getMessage(), new Date());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorPayload handleGeneric(Exception e) {

        logger.error("Server Error: NERV!", e);
        return new ErrorPayload("Server Error: NERV!", new Date());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorPayload handleAccessDenied(AccessDeniedException e) {
        return new ErrorPayload(e.getMessage(), new Date());
    }

}

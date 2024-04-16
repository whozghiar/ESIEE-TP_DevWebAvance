package fr.unilasalle.tp_garage_auto.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Gestionnaire pour DBException
    @ExceptionHandler(DBException.class)
    public ResponseEntity<API_ERRORS> handleDBException(DBException e) {
        List<String> errors = Collections.singletonList(e.getMessage());
        API_ERRORS body = new API_ERRORS(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'accès à la base de données", errors);
        body.getErrors_msg().forEach(msg ->{
            log.error(body.getType() + " : \n\t" + msg);
        });
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Gestionnaire pour NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<API_ERRORS> handleNotFoundException(NotFoundException e, WebRequest request) {
        API_ERRORS body = new API_ERRORS(LocalDateTime.now(), HttpStatus.NOT_FOUND, "Ressource non trouvée", Collections.singletonList(e.getMessage()));
        body.getErrors_msg().forEach(msg ->{
            log.error(body.getType() + " : \n\t" + msg);
        });
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Gestionnaire pour ServiceException
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<API_ERRORS> handleServiceException(ServiceException e, WebRequest request) {
        API_ERRORS body = new API_ERRORS(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Erreur de service", Collections.singletonList(e.getMessage()));
        body.getErrors_msg().forEach(msg ->{
            log.error(body.getType() + " : \n\t" + msg);
        });
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Gestionnaire pour DTOException
    @ExceptionHandler(DTOException.class)
    public ResponseEntity<API_ERRORS> handleDTOException(DTOException e, WebRequest request) {
        API_ERRORS body = new API_ERRORS(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Erreur de DTO", Collections.singletonList(e.getMessage()));
        body.getErrors_msg().forEach(msg ->{
            log.error(body.getType() + " : \n\t" + msg);
        });
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Gestionnaire pour AccessDeniedException
    @ExceptionHandler({AccessDeniedException.class, HttpClientErrorException.Forbidden.class})
    public ResponseEntity<API_ERRORS> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        API_ERRORS body = new API_ERRORS(LocalDateTime.now(), HttpStatus.FORBIDDEN, "Accès interdit", Collections.singletonList(e.getMessage()));
        body.getErrors_msg().forEach(msg ->{
            log.error(body.getType() + " : \n\t" + msg);
        });
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    // Gestionnaire pour UnauthorizedException
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<API_ERRORS> handleForbiddenException(HttpClientErrorException.Unauthorized e, WebRequest request) {
        API_ERRORS body = new API_ERRORS(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, "Accès non authorisé", Collections.singletonList(e.getMessage()));
        body.getErrors_msg().forEach(msg ->{
            log.error(body.getType() + " : \n\t" + msg);
        });
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }


    // Gestionnaire de RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<API_ERRORS> handleRuntimeException(RuntimeException e, WebRequest request) {
        API_ERRORS body = new API_ERRORS(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "Erreur interne", Collections.singletonList(e.getMessage()));
        body.getErrors_msg().forEach(msg ->{
            log.error(body.getType() + " : \n\t" + msg);
        });
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
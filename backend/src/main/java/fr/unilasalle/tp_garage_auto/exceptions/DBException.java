package fr.unilasalle.tp_garage_auto.exceptions;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class DBException extends RuntimeException{
    private String message;
    private Throwable cause;
}

package fr.unilasalle.tp_garage_auto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServiceException extends Exception{
    private String message;
    private Throwable cause;
}

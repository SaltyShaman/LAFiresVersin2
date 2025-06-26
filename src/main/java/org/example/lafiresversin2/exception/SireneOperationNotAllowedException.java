package org.example.lafiresversin2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SireneOperationNotAllowedException extends RuntimeException {
    public SireneOperationNotAllowedException(String message) {
        super(message);
    }
}
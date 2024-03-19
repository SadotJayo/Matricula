package com.mitocode.matricula.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomErrorResponse (
        LocalDateTime dateTime,
        String message,
        String path
){
}

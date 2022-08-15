package me.dio.academia.digital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionMatriculaNaoEncontrado extends Exception{
    public ExceptionMatriculaNaoEncontrado(Long id) {
        super("Matrícula não encontrada com ID " + id);
    }
}

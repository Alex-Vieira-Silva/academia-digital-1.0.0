package me.dio.academia.digital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionAvaliacaoFisicaNaoEncontrado extends Exception {
    public ExceptionAvaliacaoFisicaNaoEncontrado(Long id) {
        super("Avaliação Física não encontrada com ID " + id);
    }
}

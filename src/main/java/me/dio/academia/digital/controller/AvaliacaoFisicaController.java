package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.exception.ExceptionAlunoNaoEncontrado;
import me.dio.academia.digital.exception.ExceptionAvaliacaoFisicaNaoEncontrado;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.impl.AvaliacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.LinkLoopException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoServiceImpl service;

    @GetMapping("/avaliacao/{id}")
    public AvaliacaoFisica get(Long id){
        return service.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AvaliacaoFisica create(@Valid @RequestBody AvaliacaoFisicaForm form) throws ExceptionAvaliacaoFisicaNaoEncontrado {
        return service.create(form);
    }

    @GetMapping("/todas/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAll(@PathVariable Long id){
        return service.getAll();
    }

    @PutMapping("/alterar/{id}")
    public AvaliacaoFisica update(@PathVariable Long id, @RequestBody AvaliacaoFisicaUpdateForm avaliacaoFisicaUpdateForm) throws ExceptionAvaliacaoFisicaNaoEncontrado {
        return service.update(id, avaliacaoFisicaUpdateForm);
    }

    @DeleteMapping("/apagar/{id}")
    public void delete(@PathVariable Long id) throws ExceptionAvaliacaoFisicaNaoEncontrado {
        service.delete(id);
    }
}

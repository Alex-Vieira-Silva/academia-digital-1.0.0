package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.exception.ExceptionMatriculaNaoEncontrado;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaServiceImpl matriculaService;

    @GetMapping
    public List<Matricula> getAll(@RequestParam(value = "bairro", required = false) String bairro){
        return matriculaService.getAll(bairro);
    }
    @PostMapping
    public Matricula create(@Valid @RequestBody MatriculaForm form) throws ExceptionMatriculaNaoEncontrado {
        return matriculaService.create(form);
    }

    @GetMapping("/matriculas/{id}")
    public Matricula getAllMatriculaId(@PathVariable Long id){
        return matriculaService.get(id);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws ExceptionMatriculaNaoEncontrado {
        matriculaService.delete(id);
    }
}

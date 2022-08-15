package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exception.ExceptionAlunoNaoEncontrado;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public Aluno create(AlunoForm form) {

        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());
        return repository.save(aluno);

    }

    @Override
    public Aluno get(Long id) {

        return repository.findById(id).get();
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {

        if(dataDeNascimento == null){
            return repository.findAll();
        }else {
            LocalDate localdate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return repository.findByDataDeNascimento(localdate);
        }

    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) throws ExceptionAlunoNaoEncontrado {

        Aluno aluno = repository.findById(id).orElse(null);
        aluno.setNome(formUpdate.getNome());
        aluno.setBairro(formUpdate.getBairro());
        aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());


        /**
         * Validando o Aluno.
         * @Autor Alex Vieira
         */
        if(aluno == null){
            throw new ExceptionAlunoNaoEncontrado(aluno.getId());
        }

        return repository.save(aluno);
    }

    @Override
    public void delete(Long id) throws ExceptionAlunoNaoEncontrado {

        Aluno aluno = repository.findById(id).get();

        /**
         * Validando o Aluno.
         * @Autor Alex Vieira
         */
        if(aluno == null){
            throw new ExceptionAlunoNaoEncontrado(aluno.getId());
        }

        repository.deleteById(id);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) throws ExceptionAlunoNaoEncontrado {

        Aluno aluno = repository.findById(id).get();

        /**
         * Validando o Aluno.
         * @Autor Alex Vieira
         */
        if(aluno == null){

            throw new ExceptionAlunoNaoEncontrado(aluno.getId());
        }

        return aluno.getAvaliacoes();
    }
}

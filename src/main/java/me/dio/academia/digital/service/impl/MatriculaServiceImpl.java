package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.exception.ExceptionMatriculaNaoEncontrado;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaServiceImpl implements IMatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) throws ExceptionMatriculaNaoEncontrado {
        Matricula matricula = new Matricula();
        Aluno aluno = alunoRepository.getById(form.getAlunoId());
        matricula.setAluno(aluno);

        if(matricula == null){
            throw new ExceptionMatriculaNaoEncontrado(matricula.getId());
        }

        return matriculaRepository.save(matricula);

    }

    @Override
    public Matricula get(Long id){
        return matriculaRepository.findById(id).get();
    }

    @Override
    public List<Matricula> getAll(String bairro) {

        if(bairro == null){
            return matriculaRepository.findAll();
        }else {
            return matriculaRepository.findByAlunoBairro(bairro);
        }

    }

    @Override
    public Matricula getAllMatriculaId(Long id) throws ExceptionMatriculaNaoEncontrado {
        Matricula matricula = matriculaRepository.findById(id).get();

        /**
         * Validando a Matrícula do Aluno.
         * @Autor Alex Vieira
         */
        if(matricula == null){

            throw new ExceptionMatriculaNaoEncontrado(matricula.getId());
        }
        return matriculaRepository.getById(matricula.getId());
    }

    @Override
    public void delete(Long id) throws ExceptionMatriculaNaoEncontrado {

        Matricula matricula = matriculaRepository.findById(id).get();

        /**
         * Validando a Matrícula do Aluno.
         * @Autor Alex Vieira
         */
        if(matricula == null){
            throw new ExceptionMatriculaNaoEncontrado(matricula.getId());
        }

        matriculaRepository.deleteById(id);
    }
}

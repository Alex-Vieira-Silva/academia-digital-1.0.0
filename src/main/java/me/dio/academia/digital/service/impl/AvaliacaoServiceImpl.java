package me.dio.academia.digital.service.impl;



import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.exception.ExceptionAvaliacaoFisicaNaoEncontrado;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoServiceImpl implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) throws ExceptionAvaliacaoFisicaNaoEncontrado {

        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

        /**
         * Validando  a Avaliação Física do Aluno.
         * @Autor Alex Vieira
         */
        if(aluno == null){

            throw new ExceptionAvaliacaoFisicaNaoEncontrado(aluno.getId());
        }

        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());
        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    @Override
    public AvaliacaoFisica get(Long id) {

        return avaliacaoFisicaRepository.findById(id).get();

    }

    @Override
    public List<AvaliacaoFisica> getAll() {

        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) throws ExceptionAvaliacaoFisicaNaoEncontrado {

        AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository.findById(id).orElse(null);
        avaliacaoFisica.setPeso(formUpdate.getPeso());
        avaliacaoFisica.setAltura(formUpdate.getAltura());

        if(avaliacaoFisica == null){
            throw new ExceptionAvaliacaoFisicaNaoEncontrado(avaliacaoFisica.getId());
        }

        return avaliacaoFisicaRepository.save(avaliacaoFisica);

    }

    @Override
    public void delete(Long id) throws ExceptionAvaliacaoFisicaNaoEncontrado {

        AvaliacaoFisica avaliacao = avaliacaoFisicaRepository.findById(id).get();

        /**
         * Validando a Avaliação Física do Aluno.
         * @Autor Alex Vieira
         */
        if(avaliacao == null){
            throw new ExceptionAvaliacaoFisicaNaoEncontrado(avaliacao.getId());
        }

        avaliacaoFisicaRepository.deleteById(id);

    }


}

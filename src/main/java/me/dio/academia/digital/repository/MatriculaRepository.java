package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    /**
     * @param bairro: bairro referência para filtro
     * @return Lista de alunos matrículados que residem no bairro passado como parâmetro.
     * @Autor Alex Vieira
     */

    List<Matricula> findByAlunoBairro(String bairro);
}

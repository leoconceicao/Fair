package com.furb.projeto.repositories;

import com.furb.projeto.models.CidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Integer> {

    CidadeModel findByIdCidade(Integer idCidade);
}

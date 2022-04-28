package com.furb.projeto.repositories;

import com.furb.projeto.models.EstadoModel;
import com.furb.projeto.models.LojaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<LojaModel, Integer> {

    LojaModel findByIdLoja(Integer idLoja);
}

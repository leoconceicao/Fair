package com.furb.projeto.repositories;

import com.furb.projeto.models.ComunicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicacaoRepository extends JpaRepository<ComunicacaoModel, Integer> {
}

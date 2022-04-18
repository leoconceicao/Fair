package com.furb.projeto.repositories;

import com.furb.projeto.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {
}

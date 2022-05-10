package com.furb.projeto.repositories;

import com.furb.projeto.models.PessoaLojaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaLojaRepository extends JpaRepository<PessoaLojaModel, Integer> {

    @Query("SELECT u FROM PessoaLojaModel u WHERE u.fkPessoa = :pessoa and u.fkLoja = :loja")
    Optional<PessoaLojaModel> findById(@Param("pessoa") Integer pessoa, @Param("loja") Integer loja);
}

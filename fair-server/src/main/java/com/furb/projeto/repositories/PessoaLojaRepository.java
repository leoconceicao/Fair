package com.furb.projeto.repositories;

import com.furb.projeto.models.PessoaLojaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaLojaRepository extends JpaRepository<PessoaLojaModel, Integer> {

    @Query("SELECT u FROM pessoa_loja u WHERE u.fkPessoa = :pessoa and u.fkLoja = :loja")
    PessoaLojaModel findById(@Param("pessoa") Integer pessoa, @Param("loja") Integer loja);
}

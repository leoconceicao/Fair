package com.furb.projeto.repositories;

import com.furb.projeto.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {

    @Query("SELECT u FROM FuncionarioModel u WHERE u.fkPessoa = :pessoa and u.fkLoja = :loja")
    Optional<FuncionarioModel> findById(@Param("pessoa") Integer pessoa, @Param("loja") Integer loja);

    @Query("SELECT u FROM FuncionarioModel u WHERE u.fkPessoa.idPessoa = :idPessoa")
    Optional<FuncionarioModel> findByIdPessoa(Integer idPessoa);
}

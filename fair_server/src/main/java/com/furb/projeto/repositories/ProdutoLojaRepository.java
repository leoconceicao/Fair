package com.furb.projeto.repositories;

import com.furb.projeto.models.ProdutoLojaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoLojaRepository extends JpaRepository<ProdutoLojaModel, Integer> {

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkProduto.nome = :nome AND u.fkLoja.nome LIKE %:nomeLoja% AND u.fkProduto.active = 1")
    List<ProdutoLojaModel> findLojasByProdutoAndName(@Param("nome") String nome, @Param("nomeLoja") String nomeLoja);

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkProduto.nome = :nome AND u.fkProduto.active = 1")
    List<ProdutoLojaModel> findLojasByProduto(@Param("nome") String nome);

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkLoja.idLoja = :idLoja AND u.fkProduto.nome LIKE %:nome% AND u.fkProduto.active = 1")
    List<ProdutoLojaModel> findProdutosByLojaAndNome(@Param("idLoja") Integer idLoja, @Param("nome") String nome);

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkLoja.idLoja = :idLoja AND u.fkProduto.active = 1")
    List<ProdutoLojaModel> findProdutosByLoja(@Param("idLoja") Integer idLoja);
}

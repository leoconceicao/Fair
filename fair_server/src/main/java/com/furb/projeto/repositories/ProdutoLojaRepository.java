package com.furb.projeto.repositories;

import com.furb.projeto.models.ProdutoLojaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoLojaRepository extends JpaRepository<ProdutoLojaModel, Integer> {

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkProduto.nome = :nome AND u.fkLoja.nome LIKE %:nomeLoja%")
    List<ProdutoLojaModel> findLojasByProdutoAndName(@Param("nome") String nome, @Param("nomeLoja") String nomeLoja);

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkProduto.nome = :nome")
    List<ProdutoLojaModel> findLojasByProduto(@Param("nome") String nome);

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkLoja.idLoja = :idLoja AND u.fkProduto.nome LIKE %:nome%")
    List<ProdutoLojaModel> findProdutosByLojaAndNome(@Param("idLoja") Integer idLoja, @Param("nome") String nome);

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkLoja.idLoja = :idLoja")
    List<ProdutoLojaModel> findProdutosByLoja(@Param("idLoja") Integer idLoja);
}

package com.furb.projeto.repositories;

import com.furb.projeto.models.ProdutoLojaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoLojaRepository extends JpaRepository<ProdutoLojaModel, Integer> {

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkProduto.idProduto = :produtoId")
    List<ProdutoLojaModel> findLojasByProduto(@Param("produtoId") Integer produtoId);

    @Query("SELECT u FROM ProdutoLojaModel u WHERE u.fkProduto.idProduto = :produtoId")
    List<ProdutoLojaModel> findProdutosByLoja(@Param("produtoId") Integer produtoId);
}

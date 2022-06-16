package com.furb.projeto.repositories;

import com.furb.projeto.models.ProdutoModel;
import com.furb.projeto.models.ProdutoPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

    ProdutoModel findByIdProduto(Integer idProduto);

    @Query("SELECT u FROM ProdutoModel u WHERE u.nome LIKE %:name%")
    List<ProdutoModel> findByName(@Param("name") String name);
}

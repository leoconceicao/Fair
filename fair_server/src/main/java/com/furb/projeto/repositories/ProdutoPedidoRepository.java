package com.furb.projeto.repositories;

import com.furb.projeto.models.ProdutoPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedidoModel, Integer> {

    @Query("SELECT u FROM ProdutoPedidoModel u WHERE u.fkProduto = :produto and u.fkPedido = :pedido")
    Optional<ProdutoPedidoModel> findById(@Param("produto") Integer produto, @Param("pedido") Integer pedido);

    @Query("SELECT u FROM ProdutoPedidoModel u JOIN ProdutoModel p on u.fkProduto = p.idProduto")
    List<Object> findProdutosForPedido();

    @Query("SELECT u FROM ProdutoPedidoModel u JOIN ProdutoModel p on u.fkProduto = p.idProduto WHERE p.nome LIKE %:name%")
    List<ProdutoPedidoModel> findByProductName(@Param("name") String name);
}

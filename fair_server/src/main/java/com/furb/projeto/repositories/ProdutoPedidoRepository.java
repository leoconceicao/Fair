package com.furb.projeto.repositories;

import com.furb.projeto.models.ProdutoPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedidoModel, Integer> {

    @Query("SELECT u FROM ProdutoPedidoModel u WHERE u.fkProduto = :produto and u.fkPedido = :pedido")
    Optional<ProdutoPedidoModel> findById(@Param("produto") Integer produto, @Param("pedido") Integer pedido);
}

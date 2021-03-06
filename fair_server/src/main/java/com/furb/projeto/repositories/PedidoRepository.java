package com.furb.projeto.repositories;

import com.furb.projeto.models.PedidoModel;
import com.furb.projeto.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    PedidoModel findByIdPedido(Integer idPedido);

    @Query("SELECT u FROM PedidoModel u JOIN ProdutoPedidoModel p ON p.fkPedido.idPedido = u.idPedido WHERE p.fkProduto.nome LIKE %:name% AND u.fkCliente.idPessoa = :userId")
    List<ProdutoModel> findByName(@Param("name") String name, @Param("userId") Integer userId);

    @Query("SELECT u FROM PedidoModel u WHERE u.fkCliente.idPessoa = :userId")
    List<PedidoModel> findPedidos(@Param("userId") Integer userId);

    @Query("SELECT u FROM PedidoModel u WHERE u.fkVendedor.idLoja = :lojaId")
    List<PedidoModel> findVendas(@Param("lojaId") Integer lojaId);
}

package com.furb.projeto.repositories;

import com.furb.projeto.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    PedidoModel findByIdPedido(Integer idPedido);
    @Query("SELECT u FROM PedidoModel u")
    List<PedidoModel> findPedidos();
}

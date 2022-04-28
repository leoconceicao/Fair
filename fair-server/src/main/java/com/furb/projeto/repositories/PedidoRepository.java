package com.furb.projeto.repositories;

import com.furb.projeto.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    PedidoModel findByIdPedido(Integer idPedido);
}

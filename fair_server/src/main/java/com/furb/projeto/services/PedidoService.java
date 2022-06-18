package com.furb.projeto.services;

import com.furb.projeto.models.PedidoModel;
import com.furb.projeto.models.ProdutoModel;
import com.furb.projeto.models.ProdutoPedidoModel;
import com.furb.projeto.repositories.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Page<PedidoModel> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public Optional<PedidoModel> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public List<PedidoModel> findPedidos(Integer userId) {
        return pedidoRepository.findPedidos(userId);
    }

    public List<PedidoModel> findVendas(Integer lojaId) {
        return pedidoRepository.findVendas(lojaId);
    }

    public List<ProdutoModel> findByName(String name, Integer userId) {
        return pedidoRepository.findByName(name, userId);
    }

    @Transactional
    public PedidoModel save(PedidoModel pedidoModel) {
        return pedidoRepository.save(pedidoModel);
    }

    @Transactional
    public void delete(PedidoModel pedidoModel) {
        pedidoRepository.delete(pedidoModel);
    }
}

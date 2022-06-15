package com.furb.projeto.services;

import com.furb.projeto.models.PedidoModel;
import com.furb.projeto.repositories.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public PedidoModel save(PedidoModel pedidoModel) {
        return pedidoRepository.save(pedidoModel);
    }

    @Transactional
    public void delete(PedidoModel pedidoModel) {
        pedidoRepository.delete(pedidoModel);
    }
}
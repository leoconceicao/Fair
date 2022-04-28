package com.furb.projeto.services;

import com.furb.projeto.models.ProdutoPedidoModel;
import com.furb.projeto.repositories.ProdutoPedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProdutoPedidoService {

    final ProdutoPedidoRepository produtoPedidoRepository;

    public ProdutoPedidoService(ProdutoPedidoRepository produtoPedidoRepository) {
        this.produtoPedidoRepository = produtoPedidoRepository;
    }

    public Page<ProdutoPedidoModel> findAll(Pageable pageable) {
        return produtoPedidoRepository.findAll(pageable);
    }

    public Optional<ProdutoPedidoModel> findById(Integer id) {
        return produtoPedidoRepository.findById(id);
    }
    public Optional<ProdutoPedidoModel> findById(Integer produto, Integer pedido) {
        return produtoPedidoRepository.findById(produto, pedido);
    }

    @Transactional
    public ProdutoPedidoModel save(ProdutoPedidoModel produtoPedidoModel) {
        return produtoPedidoRepository.save(produtoPedidoModel);
    }

    @Transactional
        public void delete(ProdutoPedidoModel produtoPedidoModel) {
        produtoPedidoRepository.delete(produtoPedidoModel);
    }
}

package com.furb.projeto.services;

import com.furb.projeto.models.ProdutoModel;
import com.furb.projeto.models.ProdutoPedidoModel;
import com.furb.projeto.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<ProdutoModel> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public List<ProdutoModel> findDistinctProducts() {
        return produtoRepository.findDistinctProducts();
    }

    public Optional<ProdutoModel> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    public List<ProdutoModel> findByName(String name) {
        return produtoRepository.findByName(name);
    }

    @Transactional
    public ProdutoModel save(ProdutoModel parkingSpotModel) {
        return produtoRepository.save(parkingSpotModel);
    }

    @Transactional
    public void delete(ProdutoModel parkingSpotModel) {
        produtoRepository.delete(parkingSpotModel);
    }
}

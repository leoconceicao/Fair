package com.furb.projeto.services;

import com.furb.projeto.models.ProdutoLojaModel;
import com.furb.projeto.repositories.ProdutoLojaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoLojaService {

    final ProdutoLojaRepository produtoLojaRepository;

    public ProdutoLojaService(ProdutoLojaRepository produtoLojaRepository) {
        this.produtoLojaRepository = produtoLojaRepository;
    }

    public Page<ProdutoLojaModel> findAll(Pageable pageable) {
        return produtoLojaRepository.findAll(pageable);
    }

    public Optional<ProdutoLojaModel> findById(Integer id) {
        return produtoLojaRepository.findById(id);
    }

    public List<ProdutoLojaModel> findLojasByProduto(String nome) {
        return produtoLojaRepository.findLojasByProduto(nome);
    }

    public List<ProdutoLojaModel> findProdutosByLoja(Integer idLoja) {
        return produtoLojaRepository.findProdutosByLoja(idLoja);
    }

    @Transactional
    public ProdutoLojaModel save(ProdutoLojaModel produtoPedidoModel) {
        return produtoLojaRepository.save(produtoPedidoModel);
    }

    @Transactional
        public void delete(ProdutoLojaModel produtoPedidoModel) {
        produtoLojaRepository.delete(produtoPedidoModel);
    }
}

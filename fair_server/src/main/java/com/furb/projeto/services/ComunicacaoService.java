package com.furb.projeto.services;

import com.furb.projeto.models.ComunicacaoModel;
import com.furb.projeto.repositories.ComunicacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ComunicacaoService {

    final ComunicacaoRepository comunicacaoRepository;

    public ComunicacaoService(ComunicacaoRepository comunicacaoRepository) {
        this.comunicacaoRepository = comunicacaoRepository;
    }

    public Page<ComunicacaoModel> findAll(Pageable pageable) {
        return comunicacaoRepository.findAll(pageable);
    }

    public Optional<ComunicacaoModel> findById(Integer id) {
        return comunicacaoRepository.findById(id);
    }

    @Transactional
    public ComunicacaoModel save(ComunicacaoModel comunicacaoModel) {
        return comunicacaoRepository.save(comunicacaoModel);
    }

    @Transactional
    public void delete(ComunicacaoModel comunicacaoModel) {
        comunicacaoRepository.delete(comunicacaoModel);
    }
}

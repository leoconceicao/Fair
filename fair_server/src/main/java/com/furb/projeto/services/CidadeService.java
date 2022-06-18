package com.furb.projeto.services;

import com.furb.projeto.models.CidadeModel;
import com.furb.projeto.repositories.CidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CidadeService {

    final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Page<CidadeModel> findAll(Pageable pageable) {
        return cidadeRepository.findAll(pageable);
    }

    public Optional<CidadeModel> findById(Integer id) {
        return cidadeRepository.findById(id);
    }

    @Transactional
    public CidadeModel save(CidadeModel cidadeModel) {
        return cidadeRepository.save(cidadeModel);
    }

    @Transactional
    public void delete(CidadeModel cidadeModel) {
        cidadeRepository.delete(cidadeModel);
    }
}

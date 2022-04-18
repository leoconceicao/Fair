package com.furb.projeto.services;

import com.furb.projeto.models.PessoaModel;
import com.furb.projeto.repositories.PessoaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PessoaService {

    final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Page<PessoaModel> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Optional<PessoaModel> findById(Integer id) {
        return pessoaRepository.findById(id);
    }

    @Transactional
    public PessoaModel save(PessoaModel parkingSpotModel) {
        return pessoaRepository.save(parkingSpotModel);
    }

    @Transactional
    public void delete(PessoaModel parkingSpotModel) {
        pessoaRepository.delete(parkingSpotModel);
    }
}

package com.furb.projeto.services;

import com.furb.projeto.models.PessoaLojaModel;
import com.furb.projeto.repositories.PessoaLojaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PessoaLojaService {

    final PessoaLojaRepository pessoaLojaRepository;

    public PessoaLojaService(PessoaLojaRepository pessoaLojaRepository) {
        this.pessoaLojaRepository = pessoaLojaRepository;
    }

    public Page<PessoaLojaModel> findAll(Pageable pageable) {
        return pessoaLojaRepository.findAll(pageable);
    }

    public Optional<PessoaLojaModel> findById(Integer pessoa, Integer loja) {
        return pessoaLojaRepository.findById(pessoa, loja);
    }

    @Transactional
    public PessoaLojaModel save(PessoaLojaModel pessoaLojaModel) {
        return pessoaLojaRepository.save(pessoaLojaModel);
    }

    @Transactional
    public void delete(PessoaLojaModel pessoaLojaModel) {
        pessoaLojaRepository.delete(pessoaLojaModel);
    }
}

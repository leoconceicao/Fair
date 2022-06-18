package com.furb.projeto.services;

import com.furb.projeto.models.FuncionarioModel;
import com.furb.projeto.repositories.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FuncionarioService {

    final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Page<FuncionarioModel> findAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Optional<FuncionarioModel> findById(Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario);
    }

    public Optional<FuncionarioModel> findById(Integer pessoa, Integer loja) {
        return funcionarioRepository.findById(pessoa, loja);
    }

    public Optional<FuncionarioModel> findByIdPessoa(Integer idPessoa) {
        return funcionarioRepository.findByIdPessoa(idPessoa);
    }

    @Transactional
    public FuncionarioModel save(FuncionarioModel funcionarioModel) {
        return funcionarioRepository.save(funcionarioModel);
    }

    @Transactional
    public void delete(FuncionarioModel funcionarioModel) {
        funcionarioRepository.delete(funcionarioModel);
    }
}

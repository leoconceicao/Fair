package com.furb.projeto.services;

import com.furb.projeto.models.EstadoModel;
import com.furb.projeto.repositories.EstadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EstadoService {

    final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Page<EstadoModel> findAll(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }

    public Optional<EstadoModel> findById(Integer id) {
        return estadoRepository.findById(id);
    }

    @Transactional
    public EstadoModel save(EstadoModel estadoModel) {
        return estadoRepository.save(estadoModel);
    }

    @Transactional
    public void delete(EstadoModel estadoModel) {
        estadoRepository.delete(estadoModel);
    }
}

package com.furb.projeto.services;

import com.furb.projeto.models.LojaModel;
import com.furb.projeto.repositories.LojaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LojaService {

    final LojaRepository lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public Page<LojaModel> findAll(Pageable pageable) {
        return lojaRepository.findAll(pageable);
    }

    public Optional<LojaModel> findById(Integer id) {
        return lojaRepository.findById(id);
    }

    @Transactional
    public LojaModel save(LojaModel parkingSpotModel) {
        return lojaRepository.save(parkingSpotModel);
    }

    @Transactional
    public void delete(LojaModel parkingSpotModel) {
        lojaRepository.delete(parkingSpotModel);
    }
}

package com.furb.projeto.services;

import com.furb.projeto.models.LogradouroModel;
import com.furb.projeto.repositories.LogradouroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LogradouroService {

    final LogradouroRepository logradouroRepository;

    public LogradouroService(LogradouroRepository logradouroRepository) {
        this.logradouroRepository = logradouroRepository;
    }

    public Page<LogradouroModel> findAll(Pageable pageable) {
        return logradouroRepository.findAll(pageable);
    }

    public Optional<LogradouroModel> findById(Integer id) {
        return logradouroRepository.findById(id);
    }

    @Transactional
    public LogradouroModel save(LogradouroModel logradouroModel) {
        return logradouroRepository.save(logradouroModel);
    }

    @Transactional
        public void delete(LogradouroModel logradouroModel) {
        logradouroRepository.delete(logradouroModel);
    }
}

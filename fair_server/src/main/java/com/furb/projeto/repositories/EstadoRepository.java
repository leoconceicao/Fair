package com.furb.projeto.repositories;

import com.furb.projeto.models.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Integer> {
    EstadoModel findByIdEstado(Integer idEstado);
}

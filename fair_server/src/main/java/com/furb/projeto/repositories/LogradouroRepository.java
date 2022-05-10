package com.furb.projeto.repositories;

import com.furb.projeto.models.LogradouroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogradouroRepository extends JpaRepository<LogradouroModel, Integer> {

    LogradouroModel findByIdLogradouro(Integer idLogradouro);
}

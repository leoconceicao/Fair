package com.furb.projeto.repositories;

import com.furb.projeto.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {

    PessoaModel findByIdPessoa(Integer idPessoa);

    PessoaModel findByEmail(String idPessoa);
}

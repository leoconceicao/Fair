package com.furb.projeto.controllers;

import com.furb.projeto.dtos.PessoaDto;
import com.furb.projeto.models.PessoaModel;
import com.furb.projeto.repositories.LogradouroRepository;
import com.furb.projeto.services.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoa")
public class PessoaController {

    final PessoaService pessoaService;

    @Autowired
    private LogradouroRepository logradouroRepository;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<Page<PessoaModel>> getPessoas(@PageableDefault(sort = "idPessoa", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoa(@PathVariable(value = "id") Integer id) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (pessoaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }

    @GetMapping("findByEmail/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "email") String email) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findByEmail(email);
        if (pessoaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }

    @PostMapping
        public ResponseEntity<Object> postPessoa(@RequestBody @Valid PessoaDto pessoaDto) {
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setFkLogradouro(logradouroRepository.findByIdLogradouro(pessoaDto.getFkLogradouro()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") Integer id) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (pessoaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        pessoaService.delete(pessoaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putPessoa(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid PessoaDto pessoaDto) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (pessoaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setFkLogradouro(logradouroRepository.findByIdLogradouro(pessoaDto.getFkLogradouro()));
        pessoaModel.setIdPessoa(pessoaModelOptional.get().getIdPessoa());
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));
    }


}

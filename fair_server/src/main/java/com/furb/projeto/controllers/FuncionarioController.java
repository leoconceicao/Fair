package com.furb.projeto.controllers;

import com.furb.projeto.dtos.FuncionarioDto;
import com.furb.projeto.models.FuncionarioModel;
import com.furb.projeto.repositories.LojaRepository;
import com.furb.projeto.repositories.PessoaRepository;
import com.furb.projeto.services.FuncionarioService;
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
@RequestMapping("/funcionario")
public class FuncionarioController {

    final FuncionarioService funcionarioService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioModel>> getFuncionarios(@PageableDefault(sort = "idFuncionario", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll(pageable));
    }

    @GetMapping("/?pessoa={pessoa}?loja={loja}")
    public ResponseEntity<Object> getFuncionario(@PathVariable(value = "pessoa") Integer pessoa, @PathVariable(value = "loja") Integer loja) {
        Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.findById(pessoa, loja);
        if (funcionarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {
        var funcionarioModel = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioDto, funcionarioModel);
        funcionarioModel.setFkPessoa(pessoaRepository.findByIdPessoa(funcionarioDto.getFkPessoa()));
        funcionarioModel.setFkLoja(lojaRepository.findByIdLoja(funcionarioDto.getFkLoja()));
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionarioModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFuncionario(@PathVariable(value = "id") Integer id) {
        Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.findById(id);
        if (funcionarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found.");
        }
        funcionarioService.delete(funcionarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putFuncionario(@PathVariable(value = "id") Integer id,
                                                @RequestBody @Valid FuncionarioDto funcionarioDto) {
        Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.findById(id);
        if (funcionarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found.");
        }
        var funcionarioModel = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioDto, funcionarioModel);
        funcionarioModel.setFkPessoa(pessoaRepository.findByIdPessoa(funcionarioDto.getFkPessoa()));
        funcionarioModel.setFkLoja(lojaRepository.findByIdLoja(funcionarioDto.getFkLoja()));
        funcionarioModel.setIdFuncionario(funcionarioModelOptional.get().getIdFuncionario());
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.save(funcionarioModel));
    }


}

package com.furb.projeto.controllers;

import com.furb.projeto.dtos.ComunicacaoDto;
import com.furb.projeto.models.ComunicacaoModel;
import com.furb.projeto.repositories.PessoaRepository;
import com.furb.projeto.repositories.ProdutoRepository;
import com.furb.projeto.services.ComunicacaoService;
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
@RequestMapping("/comunicacao")
public class ComunicacaoController {

    final ComunicacaoService comunicacaoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public ComunicacaoController(ComunicacaoService comunicacaoService) {
        this.comunicacaoService = comunicacaoService;
    }

    @GetMapping
    public ResponseEntity<Page<ComunicacaoModel>> getComunicacaos(@PageableDefault(sort = "idMensagem", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(comunicacaoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getComunicacao(@PathVariable(value = "id") Integer id) {
        Optional<ComunicacaoModel> comunicacaoModelOptional = comunicacaoService.findById(id);
        if (comunicacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunicacao not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(comunicacaoModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postComunicacao(@RequestBody @Valid ComunicacaoDto comunicacaoDto) {
        var comunicacaoModel = new ComunicacaoModel();
        BeanUtils.copyProperties(comunicacaoDto, comunicacaoModel);
        comunicacaoModel.setFkProduto(produtoRepository.findByIdProduto(comunicacaoDto.getFkProduto()));
        comunicacaoModel.setFkPessoaDestinatario(pessoaRepository.findByIdPessoa(comunicacaoDto.getFkPessoaDestinatario()));
        comunicacaoModel.setFkPessoaRemetente(pessoaRepository.findByIdPessoa(comunicacaoDto.getFkPessoaRemetente()));
        return ResponseEntity.status(HttpStatus.CREATED).body(comunicacaoService.save(comunicacaoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComunicacao(@PathVariable(value = "id") Integer id) {
        Optional<ComunicacaoModel> comunicacaoModelOptional = comunicacaoService.findById(id);
        if (comunicacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunicacao not found.");
        }
        comunicacaoService.delete(comunicacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Comunicacao deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putComunicacao(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid ComunicacaoDto comunicacaoDto) {
        Optional<ComunicacaoModel> comunicacaoModelOptional = comunicacaoService.findById(id);
        if (comunicacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunicacao not found.");
        }
        var comunicacaoModel = new ComunicacaoModel();
        BeanUtils.copyProperties(comunicacaoDto, comunicacaoModel);
        comunicacaoModel.setFkProduto(produtoRepository.findByIdProduto(comunicacaoDto.getFkProduto()));
        comunicacaoModel.setFkPessoaDestinatario(pessoaRepository.findByIdPessoa(comunicacaoDto.getFkPessoaDestinatario()));
        comunicacaoModel.setFkPessoaRemetente(pessoaRepository.findByIdPessoa(comunicacaoDto.getFkPessoaRemetente()));
        comunicacaoModel.setIdMensagem(comunicacaoModelOptional.get().getIdMensagem());
        return ResponseEntity.status(HttpStatus.OK).body(comunicacaoService.save(comunicacaoModel));
    }


}

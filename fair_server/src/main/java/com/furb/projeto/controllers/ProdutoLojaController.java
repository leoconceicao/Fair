package com.furb.projeto.controllers;

import com.furb.projeto.dtos.ProdutoLojaDto;
import com.furb.projeto.models.ProdutoLojaModel;
import com.furb.projeto.repositories.LojaRepository;
import com.furb.projeto.repositories.ProdutoRepository;
import com.furb.projeto.services.ProdutoLojaService;
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
@RequestMapping("/produtoLoja")
public class ProdutoLojaController {

    final ProdutoLojaService produtoLojaService;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoLojaController(ProdutoLojaService produtoLojaService) {
        this.produtoLojaService = produtoLojaService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoLojaModel>> getProdutoLojas(@PageableDefault(sort = "idProdutoLoja", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoLojaService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> postProdutoLoja(@RequestBody @Valid ProdutoLojaDto produtoLojaDto) {
        var produtoLojaModel = new ProdutoLojaModel();
        BeanUtils.copyProperties(produtoLojaDto, produtoLojaModel);
        produtoLojaModel.setFkProduto(produtoRepository.findByIdProduto(produtoLojaDto.getFkProduto()));
        produtoLojaModel.setFkLoja(lojaRepository.findByIdLoja(produtoLojaDto.getFkLoja()));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoLojaService.save(produtoLojaModel));
    }

    @GetMapping("findLojasByProduto/\"{nome}\"")
    public ResponseEntity<Object> findLojasByProduto(@PathVariable(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoLojaService.findLojasByProduto(nome));
    }

    @GetMapping("findLojasByProdutoAndName/\"{nome}\"/\"{nomeLoja}\"")
    public ResponseEntity<Object> findLojasByProdutoAndName(@PathVariable(value = "nome") String nome, @PathVariable(value = "nomeLoja") String nomeLoja) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoLojaService.findLojasByProdutoAndName(nome, nomeLoja));
    }

    @GetMapping("findProdutosByLoja/{id}")
    public ResponseEntity<Object> findProdutosByLoja(@PathVariable(value = "id") Integer idLoja) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoLojaService.findProdutosByLoja(idLoja));
    }

    @GetMapping("findProdutosByLoja/{id}/{nome}")
    public ResponseEntity<Object> findProdutosByLojaAndName(@PathVariable(value = "id") Integer idLoja, @PathVariable(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoLojaService.findProdutosByLojaAndNome(idLoja, nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProdutoLoja(@PathVariable(value = "id") Integer id) {
        Optional<ProdutoLojaModel> produtoLojaModelOptional = produtoLojaService.findById(id);
        if (produtoLojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoLoja not found.");
        }
        produtoLojaService.delete(produtoLojaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("ProdutoLoja deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProdutoLoja(@PathVariable(value = "id") Integer id,
                                                @RequestBody @Valid ProdutoLojaDto produtoLojaDto) {
        Optional<ProdutoLojaModel> produtoLojaModelOptional = produtoLojaService.findById(id);
        if (produtoLojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoLoja not found.");
        }
        var produtoLojaModel = new ProdutoLojaModel();
        BeanUtils.copyProperties(produtoLojaDto, produtoLojaModel);
        produtoLojaModel.setFkProduto(produtoRepository.findByIdProduto(produtoLojaDto.getFkProduto()));
        produtoLojaModel.setFkLoja(lojaRepository.findByIdLoja(produtoLojaDto.getFkProduto()));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoLojaService.save(produtoLojaModel));
    }
}

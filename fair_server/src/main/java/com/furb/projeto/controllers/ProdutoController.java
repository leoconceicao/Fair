package com.furb.projeto.controllers;

import com.furb.projeto.dtos.ProdutoDto;
import com.furb.projeto.models.ProdutoLojaModel;
import com.furb.projeto.models.ProdutoModel;
import com.furb.projeto.services.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/produto")
public class ProdutoController {

    final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoModel>> getProdutos(@PageableDefault(sort = "idProduto", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll(pageable));
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<Object> getProduto(@PathVariable(value = "id") Integer id) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (produtoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());
    }

    @GetMapping("findByName/{name}")
    public ResponseEntity<Object> findAllByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findByName(name));
    }

    @GetMapping("findDistinctProducts")
    public ResponseEntity<List<ProdutoModel>> findDistinctProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findDistinctProducts());
    }

    @PostMapping
    public ResponseEntity<Object> postProduto(@RequestBody @Valid ProdutoDto produtoDto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") Integer id) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (produtoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
        }
        produtoService.delete(produtoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProduto(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid ProdutoDto produtoDto) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (produtoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
        }
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        produtoModel.setIdProduto(produtoModelOptional.get().getIdProduto());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<Object> deactivateProduto(@PathVariable(value = "id") Integer id) {
        Optional<ProdutoModel> pmo = produtoService.findById(id);
        ProdutoModel pm = new ProdutoModel();
        pm.setIdProduto(pmo.get().getIdProduto());
        pm.setNome(pmo.get().getNome());
        pm.setPreco(pmo.get().getPreco());
        pm.setFoto(pmo.get().getFoto());
        pm.setPeso(pmo.get().getPeso());
        pm.setTipo(pmo.get().getTipo());
        pm.setValidade(pmo.get().getValidade());
        pm.setActive(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(pm));
    }


}

package com.furb.projeto.controllers;

import com.furb.projeto.dtos.ProdutoPedidoDto;
import com.furb.projeto.models.ProdutoPedidoModel;
import com.furb.projeto.repositories.LogradouroRepository;
import com.furb.projeto.repositories.PedidoRepository;
import com.furb.projeto.repositories.ProdutoRepository;
import com.furb.projeto.services.ProdutoPedidoService;
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
@RequestMapping("/produtoPedido")
public class ProdutoPedidoController {

    final ProdutoPedidoService produtoPedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoPedidoController(ProdutoPedidoService produtoPedidoService) {
        this.produtoPedidoService = produtoPedidoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoPedidoModel>> getProdutoPedidos(@PageableDefault(sort = "idProdutoPedido", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoPedidoService.findAll(pageable));
    }

    @GetMapping("/?produto={produto}?pedido={pedido}")
    public ResponseEntity<Object> getProdutoPedido(@PathVariable(value = "produto") Integer produto, @PathVariable(value = "pedido") Integer pedido) {
        Optional<ProdutoPedidoModel> produtoPedidoModelOptional = produtoPedidoService.findById(produto, pedido);
        if (produtoPedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoPedidoModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postProdutoPedido(@RequestBody @Valid ProdutoPedidoDto produtoPedidoDto) {
        var produtoPedidoModel = new ProdutoPedidoModel();
        BeanUtils.copyProperties(produtoPedidoDto, produtoPedidoModel);
        produtoPedidoModel.setFkPedido(pedidoRepository.findByIdPedido(produtoPedidoDto.getFkPedido()));
        produtoPedidoModel.setFkProduto(produtoRepository.findByIdProduto(produtoPedidoDto.getFkProduto()));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPedidoService.save(produtoPedidoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProdutoPedido(@PathVariable(value = "id") Integer id) {
        Optional<ProdutoPedidoModel> produtoPedidoModelOptional = produtoPedidoService.findById(id);
        if (produtoPedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido not found.");
        }
        produtoPedidoService.delete(produtoPedidoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("ProdutoPedido deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProdutoPedido(@PathVariable(value = "id") Integer id,
                                                @RequestBody @Valid ProdutoPedidoDto produtoPedidoDto) {
        Optional<ProdutoPedidoModel> produtoPedidoModelOptional = produtoPedidoService.findById(id);
        if (produtoPedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido not found.");
        }
        var produtoPedidoModel = new ProdutoPedidoModel();
        BeanUtils.copyProperties(produtoPedidoDto, produtoPedidoModel);
        produtoPedidoModel.setFkPedido(pedidoRepository.findByIdPedido(produtoPedidoDto.getFkPedido()));
        produtoPedidoModel.setFkProduto(produtoRepository.findByIdProduto(produtoPedidoDto.getFkProduto()));
        produtoPedidoModel.setIdProdutoPedido(produtoPedidoModelOptional.get().getIdProdutoPedido());
        return ResponseEntity.status(HttpStatus.OK).body(produtoPedidoService.save(produtoPedidoModel));
    }


}

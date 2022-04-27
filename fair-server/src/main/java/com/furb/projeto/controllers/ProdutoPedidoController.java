package com.furb.projeto.controllers;

import com.furb.projeto.dtos.ProdutoPedidoDto;
import com.furb.projeto.models.ProdutoPedidoModel;
import com.furb.projeto.services.ProdutoPedidoService;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/ProdutoPedido")
public class ProdutoPedidoController {

    final ProdutoPedidoService ProdutoPedidoService;

    public ProdutoPedidoController(ProdutoPedidoService ProdutoPedidoService) {
        this.ProdutoPedidoService = ProdutoPedidoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoPedidoModel>> getProdutoPedidos(@PageableDefault(sort = "idProdutoPedido", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoPedidoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProdutoPedido(@PathVariable(value = "produto") Integer produto, @PathVariable(value = "pedido") Integer pedido) {
        Optional<ProdutoPedidoModel> ProdutoPedidoModelOptional = ProdutoPedidoService.findById(produto, pedido);
        if (ProdutoPedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoPedidoModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postProdutoPedido(@RequestBody @Valid ProdutoPedidoDto ProdutoPedidoDto) {
        var ProdutoPedidoModel = new ProdutoPedidoModel();
        BeanUtils.copyProperties(ProdutoPedidoDto, ProdutoPedidoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoPedidoService.save(ProdutoPedidoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProdutoPedido(@PathVariable(value = "produto") Integer produto, @PathVariable(value = "pedido") Integer pedido) {
        Optional<ProdutoPedidoModel> ProdutoPedidoModelOptional = ProdutoPedidoService.findById(produto, pedido);
        if (ProdutoPedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido not found.");
        }
        ProdutoPedidoService.delete(ProdutoPedidoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("ProdutoPedido deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProdutoPedido(@PathVariable(value = "produto") Integer produto, @PathVariable(value = "pedido") Integer pedido,
                                            @RequestBody @Valid ProdutoPedidoDto ProdutoPedidoDto) {
        Optional<ProdutoPedidoModel> ProdutoPedidoModelOptional = ProdutoPedidoService.findById(produto, pedido);
        if (ProdutoPedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido not found.");
        }
        var ProdutoPedidoModel = new ProdutoPedidoModel();
        BeanUtils.copyProperties(ProdutoPedidoDto, ProdutoPedidoModel);
        ProdutoPedidoModel.setIdProdutoPedido(ProdutoPedidoModelOptional.get().getIdProdutoPedido());
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoPedidoService.save(ProdutoPedidoModel));
    }


}

package com.furb.projeto.controllers;

import com.furb.projeto.dtos.PedidoDto;
import com.furb.projeto.models.PedidoModel;
import com.furb.projeto.repositories.PessoaRepository;
import com.furb.projeto.services.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {

    final PedidoService pedidoService;

    @Autowired
    private PessoaRepository pessoaRepository;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<Page<PedidoModel>> getPedidos(@PageableDefault(sort = "idPedido", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPedido(@PathVariable(value = "id") Integer id) {
        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
        if (pedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidoModelOptional.get());
    }
    @GetMapping("/findPedidos")
    public ResponseEntity<Object> findPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findPedidos());
    }



    @PostMapping
    public ResponseEntity<Object> postPedido(@RequestBody @Valid PedidoDto pedidoDto) {
        var pedidoModel = new PedidoModel();
        BeanUtils.copyProperties(pedidoDto, pedidoModel);
        pedidoModel.setFkCliente(pessoaRepository.findByIdPessoa(pedidoDto.getFkCliente()));
        pedidoModel.setFkVendedor(pessoaRepository.findByIdPessoa(pedidoDto.getFkVendedor()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") Integer id) {
        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
        if (pedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido not found.");
        }
        pedidoService.delete(pedidoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putPedido(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid PedidoDto pedidoDto) {
        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
        if (pedidoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido not found.");
        }
        var pedidoModel = new PedidoModel();
        BeanUtils.copyProperties(pedidoDto, pedidoModel);
        pedidoModel.setIdPedido(pedidoModelOptional.get().getIdPedido());
        pedidoModel.setFkCliente(pessoaRepository.findByIdPessoa(pedidoDto.getFkCliente()));
        pedidoModel.setFkVendedor(pessoaRepository.findByIdPessoa(pedidoDto.getFkVendedor()));
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedidoModel));
    }

}

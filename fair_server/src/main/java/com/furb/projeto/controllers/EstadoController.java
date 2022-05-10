package com.furb.projeto.controllers;

import com.furb.projeto.dtos.EstadoDto;
import com.furb.projeto.models.EstadoModel;
import com.furb.projeto.services.EstadoService;
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
@RequestMapping("/estado")
public class EstadoController {

    final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<Page<EstadoModel>> getEstados(@PageableDefault(sort = "idEstado", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEstado(@PathVariable(value = "id") Integer id) {
        Optional<EstadoModel> estadoModelOptional = estadoService.findById(id);
        if (estadoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(estadoModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postEstado(@RequestBody @Valid EstadoDto estadoDto) {
        var estadoModel = new EstadoModel();
        BeanUtils.copyProperties(estadoDto, estadoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.save(estadoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstado(@PathVariable(value = "id") Integer id) {
        Optional<EstadoModel> estadoModelOptional = estadoService.findById(id);
        if (estadoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado not found.");
        }
        estadoService.delete(estadoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Estado deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putEstado(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid EstadoDto estadoDto) {
        Optional<EstadoModel> estadoModelOptional = estadoService.findById(id);
        if (estadoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado not found.");
        }
        var estadoModel = new EstadoModel();
        BeanUtils.copyProperties(estadoDto, estadoModel);
        estadoModel.setIdEstado(estadoModelOptional.get().getIdEstado());
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.save(estadoModel));
    }


}

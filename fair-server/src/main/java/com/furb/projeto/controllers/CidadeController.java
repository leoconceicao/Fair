package com.furb.projeto.controllers;

import com.furb.projeto.dtos.CidadeDto;
import com.furb.projeto.models.CidadeModel;
import com.furb.projeto.models.EstadoModel;
import com.furb.projeto.repositories.EstadoRepository;
import com.furb.projeto.services.CidadeService;
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
@RequestMapping("/cidade")
public class CidadeController {

    final CidadeService cidadeService;

    @Autowired
    private EstadoRepository estadoRepository;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<Page<CidadeModel>> getCidades(@PageableDefault(sort = "idCidade", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCidade(@PathVariable(value = "id") Integer id) {
        Optional<CidadeModel> cidadeModelOptional = cidadeService.findById(id);
        if (cidadeModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cidadeModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postCidade(@RequestBody @Valid CidadeDto cidadeDto) {
        var cidadeModel = new CidadeModel();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        cidadeModel.setFkEstado(estadoRepository.findByIdEstado(cidadeDto.getFkEstado()));
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(cidadeModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCidade(@PathVariable(value = "id") Integer id) {
        Optional<CidadeModel> cidadeModelOptional = cidadeService.findById(id);
        if (cidadeModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade not found.");
        }
        cidadeService.delete(cidadeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cidade deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putCidade(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid CidadeDto cidadeDto) {
        Optional<CidadeModel> cidadeModelOptional = cidadeService.findById(id);
        if (cidadeModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade not found.");
        }
        var cidadeModel = new CidadeModel();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        cidadeModel.setFkEstado(estadoRepository.findByIdEstado(cidadeDto.getFkEstado()));
        cidadeModel.setIdCidade(cidadeModelOptional.get().getIdCidade());
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.save(cidadeModel));
    }


}

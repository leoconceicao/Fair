package com.furb.projeto.controllers;

import com.furb.projeto.dtos.LojaDto;
import com.furb.projeto.models.LojaModel;
import com.furb.projeto.repositories.LogradouroRepository;
import com.furb.projeto.services.LojaService;
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
@RequestMapping("/loja")
public class LojaController {

    final LojaService lojaService;

    @Autowired
    private LogradouroRepository logradouroRepository;

    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    @GetMapping
    public ResponseEntity<Page<LojaModel>> getLojas(@PageableDefault(sort = "idLoja", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(lojaService.findAll(pageable));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> getLoja(@PathVariable(value = "id") Integer id) {
        Optional<LojaModel> lojaModelOptional = lojaService.findById(id);
        if (lojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(lojaModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postLoja(@RequestBody @Valid LojaDto lojaDto) {
        var lojaModel = new LojaModel();
        BeanUtils.copyProperties(lojaDto, lojaModel);
        lojaModel.setFkLogradouro(logradouroRepository.findByIdLogradouro(lojaDto.getFkLogradouro()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lojaService.save(lojaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoja(@PathVariable(value = "id") Integer id) {
        Optional<LojaModel> lojaModelOptional = lojaService.findById(id);
        if (lojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja not found.");
        }
        lojaService.delete(lojaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Loja deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putLoja(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid LojaDto lojaDto) {
        Optional<LojaModel> lojaModelOptional = lojaService.findById(id);
        if (lojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja not found.");
        }
        var lojaModel = new LojaModel();
        BeanUtils.copyProperties(lojaDto, lojaModel);
        lojaModel.setIdLoja(lojaModelOptional.get().getIdLoja());
        lojaModel.setFkLogradouro(logradouroRepository.findByIdLogradouro(lojaDto.getFkLogradouro()));
        return ResponseEntity.status(HttpStatus.OK).body(lojaService.save(lojaModel));
    }


}

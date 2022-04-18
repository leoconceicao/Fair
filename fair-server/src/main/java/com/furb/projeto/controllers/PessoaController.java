package com.furb.projeto.controllers;

import com.furb.projeto.dtos.PessoaDto;
import com.furb.projeto.models.PessoaModel;
import com.furb.projeto.services.PessoaService;
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
@RequestMapping("/pessoa")
public class PessoaController {

    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<Page<PessoaModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "idPessoa", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") Integer id){
        Optional<PessoaModel> parkingSpotModelOptional = pessoaService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid PessoaDto pessoaDto){
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Integer id){
        Optional<PessoaModel> parkingSpotModelOptional = pessoaService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        pessoaService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") Integer id,
                                                    @RequestBody @Valid PessoaDto pessoaDto){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setIdPessoa(pessoaModelOptional.get().getIdPessoa());
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));
    }



}

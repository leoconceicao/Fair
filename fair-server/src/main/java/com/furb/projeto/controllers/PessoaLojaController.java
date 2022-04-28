package com.furb.projeto.controllers;

import com.furb.projeto.dtos.PessoaLojaDto;
import com.furb.projeto.models.PessoaLojaModel;
import com.furb.projeto.repositories.LojaRepository;
import com.furb.projeto.repositories.PessoaRepository;
import com.furb.projeto.services.PessoaLojaService;
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
@RequestMapping("/pessoaLoja")
public class PessoaLojaController {

    final PessoaLojaService pessoaLojaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public PessoaLojaController(PessoaLojaService pessoaLojaService) {
        this.pessoaLojaService = pessoaLojaService;
    }

    @GetMapping
    public ResponseEntity<Page<PessoaLojaModel>> getPessoaLojas(@PageableDefault(sort = "idPessoaLoja", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaLojaService.findAll(pageable));
    }

    @GetMapping("/?pessoa={pessoa}?loja={loja}")
    public ResponseEntity<Object> getPessoaLoja(@PathVariable(value = "pessoa") Integer pessoa, @PathVariable(value = "loja") Integer loja) {
        Optional<PessoaLojaModel> pessoaLojaModelOptional = pessoaLojaService.findById(pessoa, loja);
        if (pessoaLojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PessoaLoja not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaLojaModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postPessoaLoja(@RequestBody @Valid PessoaLojaDto pessoaLojaDto) {
        var pessoaLojaModel = new PessoaLojaModel();
        BeanUtils.copyProperties(pessoaLojaDto, pessoaLojaModel);
        pessoaLojaModel.setFkPessoa(pessoaRepository.findByIdPessoa(pessoaLojaDto.getFkPessoa()));
        pessoaLojaModel.setFkLoja(lojaRepository.findByIdLoja(pessoaLojaDto.getFkLoja()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaLojaService.save(pessoaLojaModel));
    }

    @DeleteMapping("/?pessoa={pessoa}?loja={loja}")
    public ResponseEntity<Object> deletePessoaLoja(@PathVariable(value = "pessoa") Integer pessoa, @PathVariable(value = "loja") Integer loja) {
        Optional<PessoaLojaModel> pessoaLojaModelOptional = pessoaLojaService.findById(pessoa, loja);
        if (pessoaLojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PessoaLoja not found.");
        }
        pessoaLojaService.delete(pessoaLojaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("PessoaLoja deleted successfully.");
    }

    @PutMapping("/?pessoa={pessoa}?loja={loja}")
    public ResponseEntity<Object> putPessoaLoja(@PathVariable(value = "pessoa") Integer pessoa, @PathVariable(value = "loja") Integer loja,
                                                @RequestBody @Valid PessoaLojaDto pessoaLojaDto) {
        Optional<PessoaLojaModel> pessoaLojaModelOptional = pessoaLojaService.findById(pessoa, loja);
        if (pessoaLojaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PessoaLoja not found.");
        }
        var pessoaLojaModel = new PessoaLojaModel();
        BeanUtils.copyProperties(pessoaLojaDto, pessoaLojaModel);
//        pessoaLojaModel.setIdPessoaLoja(pessoaLojaModelOptional.get().getIdPessoaLoja());
        return ResponseEntity.status(HttpStatus.OK).body(pessoaLojaService.save(pessoaLojaModel));
    }


}

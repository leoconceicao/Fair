package com.furb.projeto.controllers;

import com.furb.projeto.dtos.LogradouroDto;
import com.furb.projeto.models.LogradouroModel;
import com.furb.projeto.services.LogradouroService;
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
@RequestMapping("/logradouro")
public class LogradouroController {

    final LogradouroService logradouroService;

    public LogradouroController(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    @GetMapping
    public ResponseEntity<Page<LogradouroModel>> getLogradouros(@PageableDefault(sort = "idLogradouro", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(logradouroService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLogradouro(@PathVariable(value = "id") Integer id) {
        Optional<LogradouroModel> logradouroModelOptional = logradouroService.findById(id);
        if (logradouroModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Logradouro not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(logradouroModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> postLogradouro(@RequestBody @Valid LogradouroDto logradouroDto) {
        var logradouroModel = new LogradouroModel();
        BeanUtils.copyProperties(logradouroDto, logradouroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(logradouroService.save(logradouroModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLogradouro(@PathVariable(value = "id") Integer id) {
        Optional<LogradouroModel> logradouroModelOptional = logradouroService.findById(id);
        if (logradouroModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Logradouro not found.");
        }
        logradouroService.delete(logradouroModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Logradouro deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putLogradouro(@PathVariable(value = "id") Integer id,
                                            @RequestBody @Valid LogradouroDto logradouroDto) {
        Optional<LogradouroModel> logradouroModelOptional = logradouroService.findById(id);
        if (logradouroModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Logradouro not found.");
        }
        var logradouroModel = new LogradouroModel();
        BeanUtils.copyProperties(logradouroDto, logradouroModel);
        logradouroModel.setIdLogradouro(logradouroModelOptional.get().getIdLogradouro());
        return ResponseEntity.status(HttpStatus.OK).body(logradouroService.save(logradouroModel));
    }


}

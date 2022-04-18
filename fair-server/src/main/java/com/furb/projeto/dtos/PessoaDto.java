package com.furb.projeto.dtos;

import javax.validation.constraints.NotBlank;

public class PessoaDto {

    @NotBlank
    private Integer pessoaId;
    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;

}

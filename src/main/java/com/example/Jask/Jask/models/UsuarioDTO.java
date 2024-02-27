package com.example.Jask.Jask.models;

import java.sql.Date;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long idUsuario;

    private String nome;
    private Date dataNascimento;
    private Long cpf;
}

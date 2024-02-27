package com.example.Jask.Jask.models;

import java.sql.Date;


import lombok.Data;
@Data
public class TarefaDTO {

    private Long idTarefa;
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private int idStatus;
    private Long idUsuario;

}

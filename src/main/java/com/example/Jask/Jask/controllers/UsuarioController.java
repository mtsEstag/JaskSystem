package com.example.Jask.Jask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Jask.Jask.models.Usuario;
import com.example.Jask.Jask.models.UsuarioDTO;
import com.example.Jask.Jask.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> findAll() {
        List<UsuarioDTO> lista = usuarioService.findAll();
        return lista;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Usuario usuario){
        Boolean sucesso = usuarioService.save(usuario);

        if(sucesso){
            return ResponseEntity.ok().body("usuario cadastrado com sucesso");
        }
        return ResponseEntity.badRequest().body("ja existe com este cpf");
        
    }


}

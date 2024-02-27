package com.example.Jask.Jask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Jask.Jask.models.UsuarioDTO;
import com.example.Jask.Jask.models.Usuario;
import com.example.Jask.Jask.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarioAll")
    public List<UsuarioDTO> findAll() {
        List<UsuarioDTO> lista = usuarioService.findAll();
        return lista;
    }

    @GetMapping
    public Page<Usuario> findAllPage(Pageable pageable){
        Page<Usuario> lista = usuarioService.findAllPage(pageable);
        return lista;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {

        UsuarioDTO usuarioDTO = usuarioService.findById(id);

        if (usuarioDTO.getIdUsuario() != null) {
            return ResponseEntity.ok(usuarioDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Usuario usuario) {
        Boolean sucesso = usuarioService.save(usuario);

        if (sucesso) {
            return ResponseEntity.ok().body("usuario cadastrado com sucesso");
        }
        return ResponseEntity.badRequest().body("ja existe com este cpf");

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO usuarioDTO) {

        boolean sucesso = usuarioService.update(usuarioDTO);

        if (sucesso) {
            return ResponseEntity.ok().body(usuarioDTO);
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/findByName/{nome}")
    public List<UsuarioDTO> findByName(@PathVariable String nome){
        List<UsuarioDTO> lista = usuarioService.findByName(nome);
        return lista;
    }

}

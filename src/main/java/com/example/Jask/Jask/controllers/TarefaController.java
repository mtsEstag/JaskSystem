package com.example.Jask.Jask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Jask.Jask.models.TarefaDTO;
import com.example.Jask.Jask.services.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaDTO> findAll(){
        List<TarefaDTO> list = tarefaService.findAll();
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> findById(@PathVariable Long id){
        
        
        TarefaDTO tarefaDTO = tarefaService.findById(id);

        if(tarefaDTO.getIdTarefa() != null){
            return ResponseEntity.ok(tarefaDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void save(@RequestBody TarefaDTO tarefaDTO){
        tarefaService.save(tarefaDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        tarefaService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> update(@RequestBody TarefaDTO tarefaDTO){

        boolean sucesso = tarefaService.update(tarefaDTO);
        
        if(sucesso){
            return ResponseEntity.ok().body(tarefaDTO);
        }
        else{
            return ResponseEntity.badRequest().body(null);
        }

    }

    
}




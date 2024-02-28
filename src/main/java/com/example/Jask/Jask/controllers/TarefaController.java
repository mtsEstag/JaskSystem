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

import com.example.Jask.Jask.models.TarefaDTO;
import com.example.Jask.Jask.models.Usuario;
import com.example.Jask.Jask.repositories.TarefaRepository;
import com.example.Jask.Jask.models.Status;
import com.example.Jask.Jask.models.Tarefa;
import com.example.Jask.Jask.services.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/tarefaAll")
    public List<TarefaDTO> findAll() {
        List<TarefaDTO> list = tarefaService.findAll();
        return list;
    }

    @GetMapping
    public Page<Tarefa> findAllPage(Pageable pageable) {
        Page<Tarefa> lista = tarefaService.findAllPage(pageable);
        return lista;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> findById(@PathVariable Long id) {

        TarefaDTO tarefaDTO = tarefaService.findById(id);

        if (tarefaDTO.getIdTarefa() != null) {
            return ResponseEntity.ok(tarefaDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void save(@RequestBody TarefaDTO tarefaDTO) {
        tarefaService.save(tarefaDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        tarefaService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> update(@RequestBody TarefaDTO tarefaDTO) {

        boolean sucesso = tarefaService.update(tarefaDTO);

        if (sucesso) {
            return ResponseEntity.ok().body(tarefaDTO);
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/findByUser/{id}")
    public List<TarefaDTO> findTaskByUserId(@PathVariable Long id) {

        List<TarefaDTO> lista = tarefaService.findTaskByUserId(id);

        return lista;
    }

    @GetMapping("/findByStatus/{id}")
    public List<TarefaDTO> findByStatus(@PathVariable Long id) {

        List<TarefaDTO> lista = tarefaService.findByStatus(id);

        return lista;
    }

    @PutMapping("/avancar/{id}")
    public ResponseEntity<String> avancar(@PathVariable Long id) {

        boolean sucesso = tarefaService.avancar(id);

        if (sucesso) {

            return ResponseEntity.ok("Atualizado com sucesso");
        }

        return ResponseEntity.status(500).body("Houve um erro interno no servidor");
    }

    @PutMapping("/retroceder/{id}")
    public ResponseEntity<String> retroceder(@PathVariable Long id) {

        boolean sucesso = tarefaService.retroceder(id);

        if (sucesso) {

            return ResponseEntity.ok("Atualizado com sucesso");
        }

        return ResponseEntity.status(500).body("Houve um erro interno no servidor");
    }



}

package com.example.Jask.Jask.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Jask.Jask.models.Tarefa;
import com.example.Jask.Jask.models.TarefaDTO;
import com.example.Jask.Jask.repositories.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TarefaDTO> findAll() {

        List<Tarefa> lista = tarefaRepository.findAll();

        List<TarefaDTO> listaDTO = lista.stream().map(tarefa -> modelMapper.map(tarefa, TarefaDTO.class))
                .collect(Collectors.toList());

        return listaDTO;
    }

    public Page<Tarefa> findAllPage(Pageable pageable){
        Page<Tarefa> lista = tarefaRepository.findAll(pageable);
        return lista;
    }


    public TarefaDTO findById(Long id) {

        boolean existe = tarefaRepository.existsById(id);

        if (existe) {

            Tarefa tarefa = tarefaRepository.getReferenceById(id);

            TarefaDTO tarefaDTO = modelMapper.map(tarefa, TarefaDTO.class);

            return tarefaDTO;
        }

        return new TarefaDTO();
    }


    public void save(TarefaDTO tarefaDTO) {

        Tarefa tarefa = modelMapper.map(tarefaDTO, Tarefa.class);

        tarefaRepository.save(tarefa);
    }


    public void deleteById(Long id) {

        boolean existe = tarefaRepository.existsById(id);

        if (existe) {

            tarefaRepository.deleteById(id);
        }
    }
    

    public boolean update(TarefaDTO tarefaDTO) {

        try {

            Tarefa tarefa = modelMapper.map(tarefaDTO, Tarefa.class);

            tarefaRepository.save(tarefa);

            return true;
        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());

            return false;
        }
    }

    public List<TarefaDTO> findTaskByUserId(Long id){
        List<Tarefa> lista = tarefaRepository.findTaskByUserId(id);

        List<TarefaDTO> listaDTO = lista.stream().map(tarefa -> modelMapper.map(tarefa, TarefaDTO.class))
                .collect(Collectors.toList());

        return listaDTO;
    }

    public List<TarefaDTO> findByStatus(Long id){
        List<Tarefa> lista = tarefaRepository.findByStatus(id);

        List<TarefaDTO> listaDTO = lista.stream().map(tarefa -> modelMapper.map(tarefa, TarefaDTO.class))
                .collect(Collectors.toList());

        return listaDTO;
    }
}

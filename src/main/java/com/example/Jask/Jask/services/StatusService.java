package com.example.Jask.Jask.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Jask.Jask.models.Status;
import com.example.Jask.Jask.repositories.StatusRepository;

@Service
public class StatusService {
    
    @Autowired
    private StatusRepository statusRepository;

    public List<Status> findAll(){
        List<Status> lista = statusRepository.findAll();

        return lista;
    }
}

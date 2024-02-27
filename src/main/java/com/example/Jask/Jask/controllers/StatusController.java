package com.example.Jask.Jask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Jask.Jask.models.Status;
import com.example.Jask.Jask.services.StatusService;

@RestController
@RequestMapping("status")
public class StatusController {
    
    @Autowired
    private StatusService statusService;

    @GetMapping
    public List<Status> FindAll(){
        List<Status> lista = statusService.findAll();

        return lista;
    }
}

package com.example.Jask.Jask.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.Jask.Jask.models.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {


}
